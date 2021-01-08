package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.BikeStatus;
import com.revature.beans.Role;
import com.revature.beans.User;
import com.revature.utils.ConnectionUtil;

public class BikePostgres implements BikeDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Bike create(Bike b) throws Exception {
		Bike bike = null;

		try (Connection con = cu.getConnection()) {
			con.setAutoCommit(false);

			String sql = "insert into bike(id, brand, status_id, owner_id, year, color) values (default, ?, ? , ?, ? , ?)";

			String[] keys = { "id" };
			PreparedStatement pstmt = con.prepareStatement(sql, keys);
			pstmt.setString(1, b.getBrand());
			pstmt.setInt(2, Integer.parseInt(b.getStatus().getId()));
			pstmt.setInt(3, b.getOwnerID());
			pstmt.setString(4, b.getYear());
			pstmt.setString(5, b.getColor());

			pstmt.executeUpdate();

			ResultSet results = pstmt.getGeneratedKeys();
			if (results.next()) {
				bike = b;
				b.setId(results.getInt(1));
				con.commit();
			} else {
				con.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bike;
	}

	@Override
	public Bike read(Integer id) {
	Bike bike = null;
		
		try (Connection con = cu.getConnection()) {
			String sql = "select bike.id as bike_id, brand, year, color, bike.status_id as status_id, bike_status.name as status, owner_id, users.username as owner from bike join bike_status on bike.status_id = bike_status.id join users on bike.owner_id = users.id where bike.id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet results = pstmt.executeQuery();
			
			if (results.next()) {
				bike = new Bike();
				bike.setId(results.getInt("bike_id"));
				bike.setBrand(results.getString("brand"));
				bike.setYear(results.getString("year"));
				bike.setColor(results.getString("color"));
				BikeStatus status = new BikeStatus(results.getString("status_id"));
				bike.setStatus(status);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bike;
	}

	@Override
	public void update(Bike b) {
		// TODO Auto-generated method stub

	}
/*	public void delete(Cat t) {
		try (Connection conn = cu.getConnection()) {
			// first, we need to delete the cat's special needs
			String sql = "delete from special_need where cat_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected == t.getSpecialNeeds().size()) {
				// next, we need to delete the associates between the cat
				// and its owner(s)
				sql = "delete from person_cat where cat_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getId());
				pstmt.executeUpdate();
				
				// then, we can delete the cat
				sql = "delete from cat where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(2, t.getId());
				
				rowsAffected = pstmt.executeUpdate();
				if(rowsAffected > 0)
					conn.commit();		
				else
					conn.rollback();	
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/
	@Override
	public void delete(Bike b) {
	
		try (Connection con = cu.getConnection()) {
			con.setAutoCommit(false);
			String sql = "delete from bike where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b.getId());

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				con.commit();
			} else
				con.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<Bike> List() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Bike> listAvailable() {
		ArrayList<Bike> availableBikes = new ArrayList<Bike>();
	
		try (Connection con = cu.getConnection()) {
			String sql = "select bike.id as bike_id, brand, year, color, bike.status_id as status_id, bike_status.name as status, owner_id, users.username as owner from bike join bike_status on bike.status_id = bike_status.id join users on bike.owner_id = users.id where bike.status_id = 3";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet results = pstmt.executeQuery();
			
			while(results.next()) {
				Bike bike = new Bike();
				bike.setId(results.getInt("bike_id"));
				bike.setBrand(results.getString("brand"));
				bike.setYear(results.getString("year"));
				bike.setColor(results.getString("color"));
				BikeStatus status = new BikeStatus(results.getString("status_id"));
				bike.setStatus(status);	
				availableBikes.add(bike);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return availableBikes;
	}
}
/*			
			while (rs.next()) {
				Cat cat = new Cat();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				cat.setAge(rs.getInt("age"));
				Breed b = new Breed();
				b.setId(rs.getInt("breed_id"));
				b.setName(rs.getString("breed_name"));
				cat.setBreed(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				cat.setStatus(s);
				
				cat.setSpecialNeeds(getSpecialNeedsByCatId(cat.getId(), conn));
				
				cats.add(cat);
			}*/