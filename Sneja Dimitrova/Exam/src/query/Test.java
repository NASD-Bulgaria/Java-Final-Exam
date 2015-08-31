package query;

import java.util.ArrayList;
import java.util.List;

import models.Player;

public class Test {

	public static void main(String[] args) {
		Querys quer = null;
		List<String> list = new ArrayList<String>();
		Player player = new Player();
		try {
			quer = new Querys();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		list = quer.allUserNames();
		for (String string : list) {
			System.out.println(string);
		}
		player = quer.findPassToken("user");
		System.out.println(player.getToken());
		quer.close();
	}

}
