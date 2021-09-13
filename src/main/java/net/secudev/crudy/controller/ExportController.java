package net.secudev.crudy.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import net.secudev.crudy.model.produit.ProduitRepository;
import net.secudev.crudy.utils.ExcelExporter;

@Controller
public class ExportController extends AController {

	@Autowired
	private ProduitRepository produits;
	
	@GetMapping("produit/exports")
	public String exportPage()
	{
		return "produit/exports";
	}

	@GetMapping("produit/exports/excel")	
	public void exportProduitsExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=produits_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		ExcelExporter excelExporter = new ExcelExporter(produits.findAll());

		excelExporter.export(response);
	}
}
