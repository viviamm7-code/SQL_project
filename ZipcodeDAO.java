package org.example.model1;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ZipcodeDAO {
	private DataSource dataSource;

	public ZipcodeDAO() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
			this.dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb" );

		} catch (NamingException e) {
			System.out.println( "[에러] " + e.toString() );
		}

	}

	public ArrayList<ZipcodeTO> selectZipcode3(String searchDong) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ZipcodeTO> lists = new ArrayList<>();

		try {
			conn = dataSource.getConnection();

			String sql = "select seq, zipcode, sido, gugun, dong, ri, bunji from zipcode where dong like ?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, searchDong + "%" );

			rs = pstmt.executeQuery();

			while( rs.next() ) {
				ZipcodeTO to  = new ZipcodeTO();
				to.setSeq( rs.getString( "seq" ) );
				to.setZipcode( rs.getString( "zipcode" ) );
				to.setSido( rs.getString( "sido" ) );
				to.setGugun( rs.getString( "gugun" ) );
				to.setDong( rs.getString( "dong" ) );
				to.setRi( rs.getString( "ri" ) );
				to.setBunji( rs.getString( "bunji" ) );

				lists.add( to );
			}
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
		}

		return lists;
	}

	public ArrayList<ZipcodeTO> listSido() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ZipcodeTO> lists = new ArrayList<>();

		try {
			conn = dataSource.getConnection();

			String sql = "select distinct sido from zipcode";
			pstmt = conn.prepareStatement( sql );

			rs = pstmt.executeQuery();

			while( rs.next() ) {
				ZipcodeTO to  = new ZipcodeTO();
				to.setSido( rs.getString( "sido" ) );

				lists.add( to );
			}
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
		}

		return lists;
	}

	public ArrayList<ZipcodeTO> listGugun( ZipcodeTO dto ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ZipcodeTO> lists = new ArrayList<>();

		try {
			conn = dataSource.getConnection();

			String sql = "select distinct gugun from zipcode where sido = ?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, dto.getSido() );

			rs = pstmt.executeQuery();

			while( rs.next() ) {
				ZipcodeTO to  = new ZipcodeTO();
				to.setGugun( rs.getString( "gugun" ) );

				lists.add( to );
			}
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
		}

		return lists;
	}

	public ArrayList<ZipcodeTO> listDong( ZipcodeTO dto ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ZipcodeTO> lists = new ArrayList<>();

		try {
			conn = dataSource.getConnection();

			String sql = "select distinct dong from zipcode where sido = ? and gugun = ?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, dto.getSido() );
			pstmt.setString( 2, dto.getGugun() );

			rs = pstmt.executeQuery();

			while( rs.next() ) {
				ZipcodeTO to  = new ZipcodeTO();
				to.setDong( rs.getString( "dong" ) );

				lists.add( to );
			}
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
		}

		return lists;
	}

	public ArrayList<ZipcodeTO> listAddress( ZipcodeTO dto ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ZipcodeTO> lists = new ArrayList<>();

		try {
			conn = dataSource.getConnection();

			String sql = "select seq, ri, bunji from zipcode where sido = ? and gugun = ? and dong = ?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, dto.getSido() );
			pstmt.setString( 2, dto.getGugun() );
			pstmt.setString( 3, dto.getDong() );

			rs = pstmt.executeQuery();

			while( rs.next() ) {
				ZipcodeTO to  = new ZipcodeTO();
				to.setSeq( rs.getString( "seq" ) );
				to.setRi( rs.getString( "ri" ) );
				to.setBunji( rs.getString( "bunji" ) );

				lists.add( to );
			}
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
		}

		return lists;
	}
}
