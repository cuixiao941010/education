package com.cx.edu.user.service.impl;

import com.cx.edu.base.service.impl.BaseServiceImpl;
import com.cx.edu.entity.user.User;
import com.cx.edu.entity.user.enums.RoleEnum;
import com.cx.edu.exception.BusinessException;
import com.cx.edu.exception.ResultEnum;
import com.cx.edu.jwt.JwtToken;
import com.cx.edu.jwt.UserContext;
import com.cx.edu.redis.service.RedisService;
import com.cx.edu.user.model.LoginCondition;
import com.cx.edu.user.model.LoginDTO;
import com.cx.edu.user.model.RegisterCondition;
import com.cx.edu.user.model.UsersDTO;
import com.cx.edu.user.repository.UserMapper;
import com.cx.edu.user.service.UserService;
import com.cx.edu.util.Digests;
import com.cx.edu.util.Encodes;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.UUID;

import static com.cx.edu.util.Digests.HASH_INTERATIONS;

@Service
@Slf4j
@SuppressWarnings("unchecked")
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RedisService redisService;

	@Override
	protected Mapper<User> getMapper() {
		return userMapper;
	}

	@Override
	public LoginDTO login(LoginCondition loginCondition) {
		User user = new User();
		user.setUserName(loginCondition.getUserName());
		User userSearch = userMapper.selectOne(user);
		if (userSearch == null) {
			throw new BusinessException(ResultEnum.USER_IS_NOT_EXIT);
		}
		byte[] hashPassword = Digests.md5(loginCondition.getPassword().getBytes(), userSearch.getSalt().getBytes(), HASH_INTERATIONS);
		String hashPsw = Encodes.encodeHex(hashPassword);
		if (!hashPsw.equals(userSearch.getPassWord())) {
			throw new BusinessException(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
		}
		String token = JwtToken.createToken(userSearch.getId(), userSearch.getRole());
		UserContext.AuthorizedUser authorizedUser = new UserContext.AuthorizedUser(userSearch.getId(), userSearch.getRole());
		new UserContext(authorizedUser);
		redisService.setValue(String.valueOf(userSearch.getId()), token);
		return new LoginDTO(userSearch.getId(), userSearch.getRole(), token);
	}

	@Override
	public void logout() {
		Long userId = UserContext.getCurrentUser().getId();
		if (userId == null) {
			return;
		}
		redisService.deleteValue(String.valueOf(userId));
	}

	@Override
	public void register(RegisterCondition registerCondition) {
		User user = new User();
		user.setUserName(registerCondition.getUserName());
		User userSearch = userMapper.selectOne(user);
		if (userSearch != null) {
			throw new BusinessException(ResultEnum.USERNAME_IS_EXISTED);
		}
		String salt = UUID.randomUUID().toString().replace("-","");
		byte[] saltByte = salt.getBytes();
		byte[] hashPassword = Digests.md5(registerCondition.getPassword().getBytes(), saltByte, HASH_INTERATIONS);
		String encryPassword = Encodes.encodeHex(hashPassword);
		User userToInsert = new User();
		userToInsert.setUserName(registerCondition.getUserName());
		userToInsert.setPassWord(encryPassword);
		userToInsert.setSalt(salt);
		//TODO 默认新增普通类型用户，后期可加权限控制
		userToInsert.setRole(RoleEnum.Simple);
		userToInsert.setEmail(registerCondition.getEmail());
		userToInsert.setMobile(registerCondition.getMobile());
		userMapper.insertSelective(userToInsert);
	}

	@Override
	public List<UsersDTO> getUsers(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<UsersDTO> usersDTOS = null;
		if (RoleEnum.Simple.equals(UserContext.getCurrentUser().getRole())) {
			usersDTOS = userMapper.getUsers(null, UserContext.getCurrentUser().getId());
		}else if (RoleEnum.Manager.equals(UserContext.getCurrentUser().getRole())) {
			usersDTOS = userMapper.getUsers(RoleEnum.Simple, UserContext.getCurrentUser().getId());
		}
		return usersDTOS;
	}

	@Override
	public void deleteUser(Long userId) {
		Long currentUserId = UserContext.getCurrentUser().getId();
		if (userId.equals(currentUserId)) {
			throw new BusinessException(ResultEnum.DONT_DELETE_SELF);
		}
		if (RoleEnum.Simple.equals(UserContext.getCurrentUser().getRole())) {
			throw new BusinessException(ResultEnum.CANT_DELETE);
		}
		User userSearch = userMapper.selectByPrimaryKey(userId);
		if (userSearch == null) {
			throw new BusinessException(ResultEnum.USER_IS_NOT_EXIT);
		}
		userMapper.delete(userSearch);
	}

}
