package com.revature.data;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.BikeStatus;
import com.revature.beans.Finance;
import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.Role;
import com.revature.beans.User;
import com.revature.utils.ConnectionUtil;

public class UserPostgres implements UserDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public User create(User u) throws Exception {
		User user = null;

		try (Connection con = cu.getConnection()) {
			con.setAutoCommit(false);

			String sql = "insert into users(id, username, passwd, user_role_id) values (default, ?, ? , ?)";

			String[] keys = { "id" };
			PreparedStatement pstmt = con.prepareStatement(sql, keys);
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getPassword());
			pstmt.setInt(3, u.getRole().getId());

			pstmt.executeUpdate();

			ResultSet results = pstmt.getGeneratedKeys();
			if (results.next()) {
				user = u;
				u.setId(results.getInt(1));
				con.commit();
			} else {
				con.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User read(Integer id) {
		User user = null;

		try (Connection con = cu.getConnection()) {
			String sql = "select users.id as user_id, users.username as username, users.passwd as passwd, users.user_role_id as user_role_id, user_role.\"name\" as user_role from users join user_role on users.user_role_id = user_role.id where users.id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet results = pstmt.executeQuery();

			if (results.next()) {
				user = new User();
				user.setId(results.getInt("user_id"));
				user.setName(results.getString("username"));

				user.setPassword(results.getString("passwd"));
				Role role = new Role(results.getInt("user_role_id"));
				user.setRole(role);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User read(String username) {
		User user = null;

		try (Connection con = cu.getConnection()) {
			String sql = "select users.id as user_id, users.username as username, users.passwd as passwd, users.user_role_id as user_role_id, user_role.\"name\" as user_role from users join user_role on users.user_role_id = user_role.id where users.username = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet results = pstmt.executeQuery();

			if (results.next()) {
				user = new User();
				user.setId(results.getInt("user_id"));
				user.setName(results.getString("username"));

				user.setPassword(results.getString("passwd"));
				Role role = new Role(results.getInt("user_role_id"));
				user.setRole(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void update(User u) {
	}

	@Override
	public void delete() {
	}

	@Override
	public Set<User> List() {
		Set<User> users = new HashSet<>();

		try (Connection con = cu.getConnection()) {
			String sql = "select users.id as user_id, users.username as username, users.passwd as passwd, users.user_role_id as user_role_id, user_role.\"name\" as user_role from users join user_role on users.user_role_id = user_role.id";

			Statement statement = con.createStatement();
			ResultSet results = statement.executeQuery(sql);

			while (results.next()) {
				User user = new User();
				user.setId(results.getInt("person_id"));
				user.setName(results.getString("username"));
				user.setPassword(results.getString("passwd"));
				Role role = new Role(results.getInt("role_id"));
				user.setRole(role);

				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public Integer makeOffer(Offer offer) {

		try (Connection con = cu.getConnection()) {
			con.setAutoCommit(false);

			String sql = "insert into offer(id, user_id, bike_id, amount) values(default, ?, ?, ?)";

			String[] keys = { "id" };
			PreparedStatement pstmt = con.prepareStatement(sql, keys);
			pstmt.setInt(1, offer.getCustomerId());
			pstmt.setInt(2, offer.getBikeId());
			pstmt.setInt(3, offer.getAmount());

			pstmt.executeUpdate();

			ResultSet results = pstmt.getGeneratedKeys();
			if (results.next()) {
				con.commit();
				return results.getInt(1);

			} else {
				con.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Finance acceptOffer(Offer offer) {
		Finance finance = null;
		try (Connection con = cu.getConnection()) {
			con.setAutoCommit(false);
			
			String sql = "UPDATE bike SET status_id = 4, owner_id = ? WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, offer.getCustomerId());
			pstmt.setInt(2, offer.getBikeId());
			Integer rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				con.commit();
			} else {
				con.rollback();
			}
			
			
			sql = "delete from offer where bike_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, offer.getBikeId());
			rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				con.commit();
			} else {
				con.rollback();
			}
			
			finance = new Finance();
			finance.setBalance(offer.getAmount());
			finance.setPaymentAmount(offer.getAmount() / 52);
			finance.setRemainingPayments(52);
			finance.setBike_id(offer.getBikeId());
			finance.setUser_id(offer.getCustomerId());

			sql = "insert into finance(id, balance, user_id, bike_id, payment_amount, remaining_payments) values(default, ?, ?, ?, ?, ?)";

			String[] keys = { "id" };
			pstmt = con.prepareStatement(sql, keys);
			pstmt.setInt(1, finance.getBalance());
			pstmt.setInt(2, finance.getUser_id());
			pstmt.setInt(3, finance.getBike_id());
			pstmt.setInt(4, finance.getPaymentAmount());
			pstmt.setInt(5, finance.getRemainingPayments());

			pstmt.executeUpdate();
			ResultSet results = pstmt.getGeneratedKeys();
			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finance;
	}

	@Override
	public Integer rejectOffer(Offer offer) {
		Integer rowsAffected = null;
		try (Connection con = cu.getConnection()) {
			con.setAutoCommit(false);

			String sql = "delete from offer where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, offer.getId());

			rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				con.commit();
			} else {
				con.rollback();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;

	}

	@Override
	public Offer getOfferById(Integer offerId) {
		Offer offer = null;
		try (Connection con = cu.getConnection()) {
			String sql = "select * from offer where offer.id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, offerId);
			ResultSet results = pstmt.executeQuery();
			if (results.next()) {
				offer = new Offer();
				offer.setId(results.getInt("id"));
				offer.setBikeId(results.getInt("bike_id"));
				offer.setCustomerId(results.getInt("user_id"));
				offer.setAmount(results.getInt("amount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offer;
	}

	@Override
	public ArrayList<Offer> listOffers() {
		ArrayList<Offer> offers = new ArrayList<Offer>();

		try (Connection con = cu.getConnection()) {
			String sql = "select * from offer";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet results = pstmt.executeQuery();

			while (results.next()) {
				Offer offer = new Offer();

				offer.setId(results.getInt("id"));
				offer.setCustomerId(results.getInt("user_id"));
				offer.setBikeId(results.getInt("bike_id"));
				offer.setAmount(results.getInt("amount"));

				offers.add(offer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return offers;
	}

	@Override
	public ArrayList<Payment> listPayments() {
		ArrayList<Payment> payments = new ArrayList<Payment>();

		try (Connection con = cu.getConnection()) {
			String sql = "select payment.id as payment_id, payment.payment_date as payment_date, payment.finance_id as finance_id, finance.balance as balance, finance.remaining_payments as amount, users.username as username from payment join finance on finance.id = payment.finance_id join users on users.id = finance.user_id";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet results = pstmt.executeQuery();

			while (results.next()) {
				Payment payment = new Payment();
				Finance finance = new Finance();
				User user = new User();

				payment.setId(results.getInt("payment_id"));
				payment.setFinance_id(results.getString("finance_id"));
				payment.setPayment_date(results.getString("payment_date"));
				finance.setPaymentAmount(results.getInt("amount"));
				finance.setBalance(results.getInt("balance"));
				user.setName(results.getString("username"));

				finance.setUser(user);
				payment.setFinance(finance);

				payments.add(payment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return payments;
	}

	@Override
	public Integer showRemainingPayments(Integer bikeId) {

		try (Connection con = cu.getConnection()) {
			String sql = "select remaining_payments from finance where bike_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bikeId);

			ResultSet results = pstmt.executeQuery();

			if (results.next()) {
				return results.getInt("remaining_payments");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
