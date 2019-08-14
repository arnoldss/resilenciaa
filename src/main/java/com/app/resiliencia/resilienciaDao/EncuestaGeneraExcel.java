package com.app.resiliencia.resilienciaDao;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import com.app.resiliencia.model.Area;
import com.app.resiliencia.model.Catalog;
import com.app.resiliencia.model.Encuesta;
import com.app.resiliencia.model.Pregunta;
import com.app.resiliencia.model.RespuestaEncuesta;
import com.app.resiliencia.model.SubArea;

public class EncuestaGeneraExcel {
	private static final EncuestaGeneraExcel INSTANCE = new EncuestaGeneraExcel();
	
	
	
	
	public boolean generateExcelFile(OutputStream fo,Encuesta encuesta,List<Catalog> respuestasUsuario){
		boolean generationSuccessful = false;		
		
		
     	 // Se crea el libro
         HSSFWorkbook libro = new HSSFWorkbook();
         int sumasubcategorias=0;
         double promedio_subarea = 0;
         int countSubcategoria = 0;
		 int sumacategoria=0;
		 int countCategoria=0;
		 double totoalGeneral=0;
		 HSSFSheet hoja = null;
		 HSSFRow  fila = null;
		 HSSFCell  celda = null;
		 HSSFRichTextString texto;
		/*Categoria*/
		 
		 
		    /* fila = hoja.createRow(1);
	        // Se crea una celda dentro de la fila
	         celda = fila.createCell((short) 0);
	        // Se crea el contenido de la celda y se mete en ella.
	         texto = new HSSFRichTextString("Lineamientos de evaluación");
	         celda.setCellValue(texto);
	         
	         fila = hoja.createRow(1);
		        // Se crea una celda dentro de la fila
		         celda = fila.createCell((short) 1);
		        // Se crea el contenido de la celda y se mete en ella.
		         texto = new HSSFRichTextString("Nivel 1  Fortalecimiento");
		         celda.setCellValue(texto);
		         
		         
		         fila = hoja.createRow(1);
			        // Se crea una celda dentro de la fila
			         celda = fila.createCell((short) 2);
			        // Se crea el contenido de la celda y se mete en ella.
			         texto = new HSSFRichTextString("Nivel 2 En Desarrollo");
			         celda.setCellValue(texto);
	     
			         fila = hoja.createRow(1);
				        // Se crea una celda dentro de la fila
				         celda = fila.createCell((short) 3);
				        // Se crea el contenido de la celda y se mete en ella.
				         texto = new HSSFRichTextString("Nivel 3 Optimo");
				         celda.setCellValue(texto);
			         
				         fila = hoja.createRow(1);
					        // Se crea una celda dentro de la fila
					         celda = fila.createCell((short) 4);
					        // Se crea el contenido de la celda y se mete en ella.
					         texto = new HSSFRichTextString("Nivel obtenido	Promedio subárea");
					         celda.setCellValue(texto);
					         
					         fila = hoja.createRow(1);
						        // Se crea una celda dentro de la fila
						         celda = fila.createCell((short) 5);
						        // Se crea el contenido de la celda y se mete en ella.
						         texto = new HSSFRichTextString("Promedio Gobierno y Estrategia");
						         celda.setCellValue(texto);
			         
				         */
				         	

				         
			         
			         
         
		 
		for(Iterator<Area> i = encuesta.getArea().iterator(); i.hasNext(); ) {
			Area item = i.next();			    
			    //System.out.println("categorias : -"+item.getId() +""+item.getName());		
			 

			    String categoria=item.getName();
			    int crear_fila=0;
			    // Se crea una hoja dentro del libro
		        hoja = libro.createSheet(item.getName());		
		        
		        sumacategoria=0;
		        countCategoria=0;
		        List<Double> listaResultadosTotales = new  ArrayList<Double>();
			    for(Iterator<SubArea> a = item.getSubarea().iterator(); a.hasNext(); ) {
			    	SubArea itemSa = a.next();
			    	countCategoria++;
			    	
			    	
			    	
			    	
					
			    	// Se crea una fila dentro de la hoja
			        /*fila = hoja.createRow(crear_fila);
			    	fila.setHeight((short) 500);
			        // Se crea una celda dentro de la fila
			        celda = fila.createCell((short) 2);*/
			    	
			    	/*fila = hoja.createRow(crear_fila);
			    	fila.setHeight((short) 500);
			        celda = fila.createCell((short) 2);
			        // Se crea el contenido de la celda y se mete en ella.
			   	    texto = new HSSFRichTextString(item.getName()+": "+itemSa.getName());
			   	    celda.setCellValue(texto);
			   	    CellRangeAddress cra = new CellRangeAddress(crear_fila, crear_fila, 0, 5);			        
			    	hoja.addMergedRegion(cra);*/
			        
			        
			    	texto = new HSSFRichTextString(item.getName()+": "+itemSa.getName());
			    	///CellRangeAddress(0,0,0,0)
			    	 fila = hoja.createRow(crear_fila);
			    	 celda = fila.createCell((short) 2);
			    	 celda.setCellValue(texto);
			    	 //COMBINO LAS CELDAS
			    	 hoja.addMergedRegion(new CellRangeAddress(0,0,0,1));
			    	 //TAMAÑO DE CELDA
			    	 hoja.autoSizeColumn(0,true);  
			    	
			    	
			    	
			    	
			       
			        
			        
			    
			        
			        
			    	sumasubcategorias=0;
			    	

		    		
			        countSubcategoria=itemSa.getPregunta().size();
			        List<Double> listaRespuestas = new  ArrayList<Double>();
			    	for(Iterator<Pregunta> b = itemSa.getPregunta().iterator(); b.hasNext(); ) {
			    		Pregunta itemPr = b.next();
			    		crear_fila++;
			    		
			    		
			    		
			    		
			    		
				    	
			    		
			    		//System.out.println("Contador : -" +crear_fila+ "  --- "+itemPr.getName());
			    		//System.out.println("Pregunta : -"+itemPr.getName()+"  -- " +crear_fila);
			    		
			    		// Se crea una fila dentro de la hoja			    		
				         fila = hoja.createRow(crear_fila);
				        // Se crea una celda dentro de la fila
				         celda = fila.createCell((short) 0);
				        // Se crea el contenido de la celda y se mete en ella.
				         texto = new HSSFRichTextString(itemPr.getName());
				         
				         celda.setCellValue(texto);
				        			        
				         int cuenta_pregunta=1;
				         
				         for(Iterator<RespuestaEncuesta> resp = itemPr.getRespuesta().iterator(); resp.hasNext(); ) {
				        	 RespuestaEncuesta itemRespuestas = resp.next();
				        	 
				        	 
				        	// Se crea una fila dentro de la hoja
						        // fila = hoja.createRow(crear_fila);
						        // Se crea una celda dentro de la fila
						         celda = fila.createCell((short) cuenta_pregunta+1);
						        // Se crea el contenido de la celda y se mete en ella.
						         texto = new HSSFRichTextString(itemRespuestas.getName());
						         celda.setCellValue(texto);
						         cuenta_pregunta++; 
				        	 
				         }
				         cuenta_pregunta=1;
				         
				         
				         
				         
			    		for(Iterator<RespuestaEncuesta> c = itemPr.getRespuesta().iterator(); c.hasNext(); ) {
			    			RespuestaEncuesta itmRes= c.next();
			    			
			    			
			    			//System.out.println("Respuesta : "+itmRes.getName());
			    			
			    			
			    			///System.out.println("Respuesta : "+itemPr.getName() +" --- "+ itmRes.getNumber());
			    			
			    			int ponderacion=matchPregunta(itmRes.getId(),respuestasUsuario);
			    			
			    			
			    			// Se crea una fila dentro de la hoja
					        // fila = hoja.createRow(crear_fila);
					        // Se crea una celda dentro de la fila
					       // celda = fila.createCell((short) cuenta_pregunta);
					        // Se crea el contenido de la celda y se mete en ella.
					       //texto = new HSSFRichTextString(itmRes.getName());
					       //celda.setCellValue(texto);
					         
					         
					         if(ponderacion!=0){    
					        	 sumasubcategorias+=ponderacion;
					        	 
					        	 listaRespuestas.add((double) ponderacion);
					        	 celda = fila.createCell((short) 8);
							    
							     texto = new HSSFRichTextString(Integer.toString(ponderacion));
							     celda.setCellValue(texto);
							    
					        	 
					         }
					         
					        			        
					         
			    		}
			    		
			    		
			    		//System.out.println("Saliendo de las lista-");
			    		
			    		
			    		
			    		 
					     
					     
			    		
			    		cuenta_pregunta++;			    		

			    	} 
			    	
			    	celda = fila.createCell((short) 9);
				     // Se crea el contenido de la celda y se mete en ella.
				     texto = new HSSFRichTextString(Integer.toString(sumasubcategorias));
				     celda.setCellValue(texto);
			    	
				    //listaRespuestas.stream().mapToDouble(sla -> sla ).sum()/listaRespuestas.size();
			    	 
			    	 
				     
				 	
				    	///System.out.println("Divicion : promedio sb area "+ promedio_subarea +" --- sumasubcategorias"+ "Count subcategoria " +countSubcategoria);
				    	
				
				     celda = fila.createCell((short) 10);
					 // Se crea el contenido de la celda y se mete en ella.
				     double sumePonderaciones=listaRespuestas.stream().mapToDouble(sla -> sla ).sum()/listaRespuestas.size();
					 texto = new HSSFRichTextString(Double.toString(sumePonderaciones));
					 celda.setCellValue(texto);
				     
				     
					  listaResultadosTotales.add(sumePonderaciones);
				     
					  totoalGeneral=listaResultadosTotales.stream().mapToDouble(sla -> sla ).sum()/listaResultadosTotales.size();
			    	 
			    	  listaRespuestas.isEmpty();
			    	
			    
				     
				     
				     
				  
				     
			    	crear_fila+=1;
			    	//System.out.println("Suma de resuestas de  sub categorias "+sumacategorias+ "Promedio " +promedio_subarea);
			    }
			    
			    
			    
			    
			     celda = fila.createCell((short) 11);
			     // Se crea el contenido de la celda y se mete en ella.
			     texto = new HSSFRichTextString(Double.toString(totoalGeneral));
			     celda.setCellValue(texto);
			    
			    
			    listaResultadosTotales.clear();
			    
			    
			    
			     
			     
			 
			    

			    
		}
		
		
		

       

       

       

       
        
        try {
        	libro.write(fo);
			//fo.close();
			generationSuccessful = true;
		} catch (IOException ioEx2) {
			ioEx2.printStackTrace();
		}
        
		
		return generationSuccessful;
		
	}
	
	public static EncuestaGeneraExcel getInstance() {
		return INSTANCE;
	}
		
	
	
	public int matchPregunta(int idrespuesta,List<Catalog> respuestasUsuario){
		int ponderacion = 0;
		
		
		
		for(Iterator<Catalog> x = respuestasUsuario.iterator(); x.hasNext(); ) {
			Catalog item = x.next();
			if(idrespuesta==item.getId()){
				ponderacion=item.getStatus();///ponderacion
				break;
			}
			
			
			
		}
		
		
		return ponderacion;
	}
	
	
	/*
	private void insertSheetIntoBook(HSSFWorkbook libro,String nombrehoja) {
		 // Se crea una hoja dentro del libro
        HSSFSheet hoja = libro.createSheet(nombrehoja);
        // Se crea una fila dentro de la hoja
        HSSFRow fila = hoja.createRow(0);
        // Se crea una celda dentro de la fila
        HSSFCell celda = fila.createCell((short) 0);
        // Se crea el contenido de la celda y se mete en ella.
        HSSFRichTextString texto = new HSSFRichTextString("nuexo excel");
        celda.setCellValue(texto);

        
	}*/
	
	

	
		
}
