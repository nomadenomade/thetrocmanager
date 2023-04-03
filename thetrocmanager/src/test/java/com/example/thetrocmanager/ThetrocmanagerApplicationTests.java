package com.example.thetrocmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.DAO.VerkauferDAO;
import com.example.DAO.userDAO;
import com.example.models.Kaufer;
import com.example.models.Verkaufer;

@SpringBootTest
class ThetrocmanagerApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void getverkaufer() {
		VerkauferDAO dao = new VerkauferDAO();
		List <Verkaufer>list = (List<Verkaufer>)(dao.getVerkaufer(null, null, "a", null).get("allsellers"));
		assertEquals(0,list.size());
	}

	@Test
	void getGroupkaufer() {
		userDAO dao = new userDAO();
		List<Kaufer> list = (List<Kaufer>)(dao.getGroupBuyers("blais", null, null, null).get("buyers"));
		assertEquals(0,list.size());
	}
}
