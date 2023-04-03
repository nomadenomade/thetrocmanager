package com.example;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.DAO.VerkauferDAO;
import com.example.DAO.userDAO;
import com.example.models.Kaufer;
import com.example.models.Produkt;
import com.example.models.Verkaufer;


@RestController
public class controller {

	@Autowired
	VerkauferDAO dao;
	
	@Autowired
	userDAO dao2;

	@GetMapping("/")
	public ModelAndView getHome() {
		return new ModelAndView("index");
	}

	@GetMapping("/allsellers")
	public String getallnames() {

		JSONObject json1 = null;
		List <Object> list1 = new ArrayList<>();

		List<Verkaufer> list = (List<Verkaufer>) dao.getAllVerkaufer().get("allsellers");
		for(Verkaufer ver: list) {
			json1 = new JSONObject();
			List<Object>listprodukt = new ArrayList<>();
			json1.put("name", ver.getPerson().getName());
			json1.put("vorname", ver.getPerson().getVorname());
			json1.put("email", ver.getPerson().getEmail());
			json1.put("geburtsdatum", ver.getPerson().getGeburtsdatum());
			json1.put("geburtsort", ver.getPerson().getGeburtsort());
			json1.put("status", ver.getStatus());
			json1.put("datum", ver.getPerson().getDatum());
			json1.put("telefon", ver.getPerson().getTelephone());
			json1.put("idverkaufer", ver.getIdVerkaufer());

			
			if(ver.getUnternehmen()!=null) {
				json1.put("nameunternehmen", ver.getUnternehmen().getName());
			}
			 JSONObject jsonprodukt = null;
			 int countprodukt =0;
			for(Produkt p: ver.getProduktList()) {
				jsonprodukt =new JSONObject();

				jsonprodukt.put("nameprodukt", p.getName());
				jsonprodukt.put("preis", p.getPreis());
				jsonprodukt.put("Status", p.getStatus());
				jsonprodukt.put("währung", p.getWährung());
				countprodukt ++;
				listprodukt.add(jsonprodukt);
			}
			System.out.println(new JSONArray(listprodukt));
			json1.put("anzahlprodukt", countprodukt);
			if(listprodukt.size()>0) {
				json1.put("listprodukte", new JSONArray(listprodukt));
			}
			
			list1.add(json1);
		}
		System.out.println( String.valueOf(new JSONArray(list1)) );
	
		return String.valueOf(new JSONArray(list1)) ;
	}
	
	@GetMapping("/all_sellers")
	public String get_list_alle_Verkaufer() {
		List<Verkaufer> listverkaufer = (List<Verkaufer>) dao.getAllVerkaufer().get("allsellers");
		
		String result = "<div class=\"table-responsive\" id=\"tabcontent\">\r\n";
				result+= "					<table class=\"table table-dark table-stripped\">\r\n";
				result+="						<thead>\r\n";
				result+= "							<tr>\r\n";
				result+= "								<th>firstname</th>\r\n";
				result+="								<th>lastname</th>\r\n";
				result+="								<th>email</th>\r\n";
				result+="								<th>phone</th>\r\n";
				result+="								<th>company name</th>\r\n";
				result+="								<th>status</th>\r\n";
				result+="								<th>date registration</th>\r\n";
				result+="								<th>activate</th>\r\n";
				result+="								<th>deactivate</th>\r\n";
				result+="								<th>delete</th>\r\n";
				result+="								<th>detail</th>\r\n";
				result+="							</tr>\r\n";
				result+="						</thead>\r\n";
				result+="						<tbody>\r\n";
				for(Verkaufer ver: listverkaufer) {
					
						
					result+="							<tr >\r\n";
					result+="								<td>"+ver.getPerson().getVorname()+"</td>\r\n";
					result+="								<td>"+ver.getPerson().getName()+"</td>\r\n";
					result+="								<td>"+ver.getPerson().getEmail()+"</td>\r\n";
					result+="								<td>"+ver.getPerson().getTelephone()+"</td>\r\n";
					if(ver.getUnternehmen()!=null) {
						result+="								<td>"+ver.getUnternehmen().getName()+"</td>\r\n";
					}else {
						result+="								<td></td>\r\n";
					}
						
					result+="								<td>"+ver.getStatus()+"</td>\r\n";
					result+="								<td>"+ver.getPerson().getDatum()+"</td>\r\n";
					result+="								<td>\r\n";
					result+="									<button ng-click=\"activate("+ver.getIdVerkaufer()+")\" style=\"width:150px; border:1px solid #228B22;border-radius:200px; padding:2px; font-size:0.9em;background-color:#228B22;color: white; margin:auto;\">activate</button>\r\n";
					result+="								</td>\r\n";
					result+="								<td>\r\n";
					result+="									<button ng-click=\"deactivate("+ver.getIdVerkaufer()+")\" style=\"width:150px; border:1px solid #FF8C00;border-radius:200px; padding:2px; font-size:0.9em;background-color:#FF8C00;color: black; margin:auto;\">deactivate</button>\r\n";
					result+="								</td>\r\n";
					result+="								<td>\r\n";
					result+="									<button ng-click=\"delete("+ver.getIdVerkaufer()+")\" style=\"width:150px; border:1px solid red;border-radius:200px; padding:2px; font-size:0.9em;background-color:red;color: white; margin:auto;\">delete</button>\r\n";
					result+="								</td>\r\n";
					result+="								<td>\r\n";
					result+="									<button ng-click=\"detail("+ver.getIdVerkaufer()+")\" style=\"width:150px; border:1px solid #6495ED;border-radius:200px; padding:2px; font-size:0.9em;background-color:#6495ED;color: white; margin:auto;\">details</button>\r\n";
					result+="								</td>\r\n";
					result+="							</tr>\r\n";
						
				}
				result+="						</tbody>\r\n";
				result+="					</table>\r\n";
				result+="				</div>\r\n";
				result+="			";
				
				
				return result;
		
	}
	
	@GetMapping("/buyers")
	public String getbuyers(@RequestParam("name")String name,@RequestParam("vorname")String vorname,@RequestParam("email") String email,@RequestParam("pseudo") String pseudo) {
		
		List<Kaufer> listkaufer = (List<Kaufer>) (dao2.getGroupBuyers(name, vorname, email, pseudo).get("buyers")) ;
				
				String result = "<div class=\"table-responsive\" id=\"tabcontent\">\r\n";
						result+= "					<table class=\"table table-dark table-stripped\">\r\n";
						result+="						<thead>\r\n";
						result+= "							<tr>\r\n";
						result+= "								<th>firstname</th>\r\n";
						result+="								<th>lastname</th>\r\n";
						result+="								<th>email</th>\r\n";
						result+="								<th>phone</th>\r\n";
						result+="								<th>pseudo</th>\r\n";
						result+="								<th>status</th>\r\n";
						result+="								<th>date registration</th>\r\n";
						result+="								<th>activate</th>\r\n";
						result+="								<th>deactivate</th>\r\n";
						result+="								<th>delete</th>\r\n";
						result+="								<th>detail</th>\r\n";
						result+="							</tr>\r\n";
						result+="						</thead>\r\n";
						result+="						<tbody>\r\n";
						for(Kaufer kaufer: listkaufer) {
							
								
							result+="							<tr >\r\n";
							result+="								<td>"+kaufer.getPerson().getVorname()+"</td>\r\n";
							result+="								<td>"+kaufer.getPerson().getName()+"</td>\r\n";
							result+="								<td>"+kaufer.getPerson().getEmail()+"</td>\r\n";
							result+="								<td>"+kaufer.getPerson().getTelephone()+"</td>\r\n";
							result+="								<td>"+kaufer.getPseudo()+"</td>\r\n";		
							result+="								<td></td>\r\n";
							result+="								<td>"+kaufer.getPerson().getDatum()+"</td>\r\n";
							result+="								<td>\r\n";
							result+="									<button ng-click=\"activate("+kaufer.getIdKaufer()+")\" style=\"width:150px; border:1px solid #228B22;border-radius:200px; padding:2px; font-size:0.9em;background-color:#228B22;color: white; margin:auto;\">activate</button>\r\n";
							result+="								</td>\r\n";
							result+="								<td>\r\n";
							result+="									<button ng-click=\"deactivate("+kaufer.getIdKaufer()+")\" style=\"width:150px; border:1px solid #FF8C00;border-radius:200px; padding:2px; font-size:0.9em;background-color:#FF8C00;color: black; margin:auto;\">deactivate</button>\r\n";
							result+="								</td>\r\n";
							result+="								<td>\r\n";
							result+="									<button ng-click=\"delete("+kaufer.getIdKaufer()+")\" style=\"width:150px; border:1px solid red;border-radius:200px; padding:2px; font-size:0.9em;background-color:red;color: white; margin:auto;\">delete</button>\r\n";
							result+="								</td>\r\n";
							result+="								<td>\r\n";
							result+="									<button ng-click=\"detail("+kaufer.getIdKaufer()+")\" style=\"width:150px; border:1px solid #6495ED;border-radius:200px; padding:2px; font-size:0.9em;background-color:#6495ED;color: white; margin:auto;\">details</button>\r\n";
							result+="								</td>\r\n";
							result+="							</tr>\r\n";
								
						}
						result+="						</tbody>\r\n";
						result+="					</table>\r\n";
						result+="				</div>\r\n";
						result+="			";
						
						
				return result;
		
		
	}
	
}
