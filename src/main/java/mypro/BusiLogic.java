package mypro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusiLogic {		
public static Connection getConnection() {	
	String url="jdbc:mysql://localhost:3306/ram_sudents";
	String uname="root";
	String pwd="ramakrishnan1@";	
	Connection con=null;
		try {
			con=DriverManager.getConnection(url, uname, pwd);}
			catch (Exception e) {
	
			e.printStackTrace();
		}
		return con;
	} 
	

public static int save(userBean u) {
	int status=0;
	try {
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("insert into lotterydb"
				+ "(phno,column1,column2,column3,column4,column5) values(?,?,?,?,?,?)");
		ps.setLong(1,u.getPhno());
		ps.setInt(2, u.getChoice1());
		ps.setInt(3, u.getChoice2());
		ps.setInt(4, u.getChoice3());
		ps.setInt(5, u.getChoice4());
		ps.setInt(6, u.getChoice5());
		status=ps.executeUpdate();}
	catch(Exception e){
		System.out.println(e);
	}
	return status;
		
}
public static List<Long> thirtyWin(int win){
	System.out.println(win);
	int win1=win;
	 List<Long> thirtyWinList=new ArrayList<>();
	 int temp=0;
	 int check=0;
		int original=0;
		for(int i=0;i<2;i++) {
			
			temp=win%10;
			check=(check*10)+temp;
			win=win/10;}
			int original1=check;
			 System.out.println(original1 + "Check");
	 
	try {
	Connection con=getConnection();
	PreparedStatement ps=con.prepareStatement("select * from lotterydb");
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
		if((rs.getInt(2)%10==win1%10) && giveTwo(rs.getInt(2))!=original1){
			thirtyWinList.add(rs.getLong(1));
		}
		if((rs.getInt(3)%10==win1%10)&& giveTwo(rs.getInt(3))!=original1){
			thirtyWinList.add(rs.getLong(1));
		}
		if((rs.getInt(4)%10==win1%10)&& giveTwo(rs.getInt(4))!=original1){
			thirtyWinList.add(rs.getLong(1));
		}
		if((rs.getInt(5)%10==win1%10)&& giveTwo(rs.getInt(5))!=original1){
			thirtyWinList.add(rs.getLong(1));
		}
		if((rs.getInt(6)%10==win1%10)&& giveTwo(rs.getInt(6))!=original1){
			thirtyWinList.add(rs.getLong(1));
		}}}
	catch (Exception e)
	{
		System.out.println(e);
	}
	System.out.println(thirtyWinList+"thirtyWinList");
	
return thirtyWinList;
	}	


public static List<Long> getFiveHunWin(int win){
	List<Long> fiveHunWin=new ArrayList<>();
	int win1=win;
	int temp=0;
	int check=0;
	int original=0;
	for(int i=0;i<2;i++) {
		temp=win1%10;
		check=(check*10)+temp;
		win1=win1/10;}
		 original=check;
		 System.out.println("Original"+original);
		 
	try {
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("select * from lotterydb");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			if(rs.getInt(2)%10==win%10 && rs.getInt(2)!=win && (giveTwo(rs.getInt(2))==original)){
				fiveHunWin.add(rs.getLong(1));
			}
			if(rs.getInt(3)%10==win%10&& rs.getInt(3)!=win && (giveTwo(rs.getInt(3))==original)){
				
				fiveHunWin.add(rs.getLong(1));
			}
			if(rs.getInt(4)%10==win%10&& rs.getInt(4)!=win && (giveTwo(rs.getInt(4))==original)){
				
				fiveHunWin.add(rs.getLong(1));
			}
			if(rs.getInt(5)%10==win%10&& rs.getInt(5)!=win && (giveTwo(rs.getInt(5))==original)){
				fiveHunWin.add(rs.getLong(1));
			}
			if(rs.getInt(6)%10==win%10&& rs.getInt(6)!=win && (giveTwo(rs.getInt(6))==original)){
				fiveHunWin.add(rs.getLong(1));
			}
		//	if(giveTwo(rs.getInt(6))==original && rs.getInt(6)!=win && rs.getInt(6)%10!=win%10) {
		//		fiveHunWin.add(rs.getLong(1));
		//	}
		}}
		catch(Exception e) {
			System.out.println(e);
		}
	System.out.println( fiveHunWin);
	return fiveHunWin;}
	
public static List<Integer> getChoiceByName(Long phon){
	List<Integer> getChoices=new ArrayList<>();
	System.out.println(phon);
	try {
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("select  * from lotterydb");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
	if(rs.getLong(1)==phon) {
	getChoices.add(rs.getInt(2));}
	if(rs.getLong(1)==phon) {
	getChoices.add(rs.getInt(3));}
	if(rs.getLong(1)==phon) {
	getChoices.add(rs.getInt(4));}
	if(rs.getLong(1)==phon) {
	getChoices.add(rs.getInt(5));}
	if(rs.getLong(1)==phon) {
		getChoices.add(rs.getInt(6));
	}
		}}
		catch(Exception e) {
			System.out.println(e);
		}
		return getChoices;
	}

public static int giveTwo(int number) {
	int temp=0;
	int check=0;
	int original1=0;
	for(int i=0;i<2;i++) {
		temp=number%10;
		check=(check*10)+temp;
		number=number/10;}
	original1=check;
	return original1;
}
public static List<Integer> getUserThirtyWin(long phoneNumber, int todayWin){
	List<Integer> getThirtyWin=new  ArrayList<>();
	int temp=0;
	int check=0;
	int original=0;
	for(int i=0;i<2;i++) {
		temp=todayWin%10;
		check=(check*10)+temp;
		todayWin=todayWin/10;}
		 original=check;
	try {
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("select * from lotterydb");
		ResultSet rs=ps.executeQuery();
	
		while(rs.next()) {
			if(rs.getInt(1)==phoneNumber) {
				if(todayWin%10==rs.getInt(2)%10 && rs.getInt(2)!=todayWin && giveTwo(rs.getInt(2))!=original){
					getThirtyWin.add(rs.getInt(2));
				}
			}
			if(rs.getInt(1)==phoneNumber) {
				if(todayWin%10==rs.getInt(3)%10&& rs.getInt(3)!=todayWin &&giveTwo(rs.getInt(3))!=original) {
					getThirtyWin.add(rs.getInt(3));
				}
			}
			if(rs.getInt(1)==phoneNumber) {
				if(todayWin%10==rs.getInt(4)%10 && rs.getInt(4)!=todayWin &&giveTwo(rs.getInt(4))!=original){
					getThirtyWin.add(rs.getInt(4));
				}
			}
			if(rs.getInt(1)==phoneNumber) {
				if(todayWin%10==rs.getInt(5)%10&& rs.getInt(2)!=todayWin &&giveTwo(rs.getInt(5))!=original) {
					getThirtyWin.add(rs.getInt(5));
				}
			}
			if(rs.getInt(1)==phoneNumber) {
				if(todayWin%10==rs.getInt(6)%10 && rs.getInt(2)!=todayWin &&giveTwo(rs.getInt(6))!=original){
					getThirtyWin.add(rs.getInt(6));
				}
			}
		}}
		catch(Exception e) {
			System.out.println(e);
		}
	System.out.println(getThirtyWin+"Hii");
	return getThirtyWin;}

public static List<Long> getWinningNumFifteenThou(int winningNum){
	List<Long> getFifteenThouWin=new ArrayList<>();
	try {
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("select * from lotterydb");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
		if(rs.getInt(2)==winningNum) {
			getFifteenThouWin.add(rs.getLong(1));
		}
		if(rs.getInt(3)==winningNum) {
			getFifteenThouWin.add(rs.getLong(1));
		}
		if(rs.getInt(4)==winningNum) {
			getFifteenThouWin.add(rs.getLong(1));
		}
		if(rs.getInt(5)==winningNum) {
			getFifteenThouWin.add(rs.getLong(1));
		}
		if(rs.getInt(6)==winningNum) {
			getFifteenThouWin.add(rs.getLong(1));
		}}}
	catch(Exception e){System.out.println(e);}
	return getFifteenThouWin;}
public static String retrievePwdDB() {
	String mpwd1=null;
	try {
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("select password from lotterydb1 where id=1");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
		mpwd1=rs.getString(1);
		}
}
	catch(Exception e) {
		System.out.println(e);
	}
	return mpwd1;
	}
}