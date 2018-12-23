package org.huxia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.huxia.bean.DashBoradPost;
import org.huxia.bean.GenderCount;
import org.huxia.util.DBUtil;

public class DashBoardPostDao {
	public List<DashBoradPost> calculatePostNumber() {
		Connection connection = DBUtil.getConnection();
		String sql = "select stuName ,count(*) from view_Post group by author;";
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		List<DashBoradPost> postcount =new ArrayList<DashBoradPost>();
		try {
			pStatement = connection.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				DashBoradPost post = new DashBoradPost();
				post.setStuName(rs.getString(1));
				post.setNumber(rs.getString(2));
				postcount.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pStatement, connection);
		}
		return postcount;
	}

}
