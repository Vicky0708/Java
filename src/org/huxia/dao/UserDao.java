package org.huxia.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.huxia.bean.User;

import org.huxia.util.DBUtil;

public class UserDao {
	public boolean create(User user) {
		Connection connection = DBUtil.getConnection();
		String sql = "INSERT INTO tb_users VALUES(?,?,?,?,?,?,?)";
		int result = 0;
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, user.getStuId());
			pStatement.setString(2, user.getStuName());
			pStatement.setString(3, user.getPassword());
			pStatement.setString(4, user.getAvatar());
			pStatement.setString(5, user.getGender());
			pStatement.setString(6, user.getBio());
			pStatement.setString(7, user.getGitUrl());
			result = pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result==1;
	}
	public User query(String stuId) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM tb_users WHERE stuId=?";
		ResultSet rs = null;
		User user = null;
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, stuId);
			rs = pStatement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setStuId(rs.getString(1));
				user.setStuName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setAvatar(rs.getString(4));
				user.setGender(rs.getString(5));
				user.setBio(rs.getString(6));
				user.setGitUrl(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
