package org.huxia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.huxia.bean.GenderCount;
import org.huxia.bean.User;
import org.huxia.util.DBUtil; 
import java.util.*;

public class GenderCountDao {
	public List<GenderCount> calculateGenderNumber() {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT gender,count(*) From tb_users group by gender";
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		List<GenderCount> gendercount =new ArrayList<GenderCount>();
		try {
			pStatement = connection.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				GenderCount	gender = new GenderCount();
				gender.setGender(rs.getString(1));
				gender.setNumber(rs.getString(2));
				gendercount.add(gender);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pStatement, connection);
		}
		return gendercount;
	}

}
