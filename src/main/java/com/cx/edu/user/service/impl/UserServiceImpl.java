package com.cx.edu.user.service.impl;

import com.cx.edu.base.service.impl.BaseServiceImpl;
import com.cx.edu.entity.user.User;
import com.cx.edu.exception.BusinessException;
import com.cx.edu.exception.ResultEnum;
import com.cx.edu.jwt.JwtToken;
import com.cx.edu.jwt.UserContext;
import com.cx.edu.redis.service.RedisService;
import com.cx.edu.user.model.LoginCondition;
import com.cx.edu.user.model.LoginDTO;
import com.cx.edu.user.repository.UserMapper;
import com.cx.edu.user.service.UserService;
import com.cx.edu.util.Digests;
import com.cx.edu.util.Encodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

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
		byte[] hashPassword = Digests.md5(loginCondition.getPassword().getBytes(), userSearch.getSalt().getBytes(), Digests.HASH_INTERATIONS);
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

}
