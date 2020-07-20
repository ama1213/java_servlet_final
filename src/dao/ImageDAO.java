package dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import bean.Constants;
import bean.Picture;

public class ImageDAO {
	public List<Picture> findAllImage() {
		List<Picture> pictureAllList = new ArrayList<Picture>();
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (
				Connection conn = DriverManager.getConnection(Constants.JDBC_URL,Constants.DB_USER,Constants.DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement("SELECT pict_id,pict_image FROM PICTURE ORDER BY pict_id");
				ResultSet rs = pstmt.executeQuery();
				) {
			while (rs.next()) {
				int pict_id = rs.getInt("pict_id");
				Blob blob = rs.getBlob("pict_image");
				byte[] b = blob.getBytes(1, (int)blob.length());
				String imageSTR = Base64.getEncoder().encodeToString(b);
				Picture sw = new Picture(pict_id, imageSTR);
				pictureAllList.add(sw);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return pictureAllList;
	}

	public boolean updateImage(int pictID, InputStream is) {
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (
				Connection conn = DriverManager.getConnection(Constants.JDBC_URL,Constants.DB_USER,Constants.DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement("UPDATE PICTURE SET pict_image = ? WHERE pict_id = ?");
				) {
			pstmt.setBlob(1, is);
			pstmt.setInt(2, pictID);
			int result = pstmt.executeUpdate();
			if (result != 1) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addImage(int pictID, InputStream is) {
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (
				Connection conn = DriverManager.getConnection(Constants.JDBC_URL,Constants.DB_USER,Constants.DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PICTURE (pict_id,pict_image) VALUES (?,?)");) {
			pstmt.setInt(1, pictID);
			pstmt.setBlob(2, is);
			int result = pstmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteImage(int pictID) {
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (
				Connection conn = DriverManager.getConnection(Constants.JDBC_URL,Constants.DB_USER,Constants.DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM PICTURE WHERE pict_id IS ?");) {
			pstmt.setInt(1, pictID);
			int result = pstmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
