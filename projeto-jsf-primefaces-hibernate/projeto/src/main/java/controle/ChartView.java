package controle;


import javax.annotation.PostConstruct;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;

import com.ibm.icu.text.SimpleDateFormat;

import banco.DAOGenerico;
import entidades.Leite;
 
@ManagedBean
@ViewScoped
public class ChartView implements Serializable {
     
    private LineChartModel dateModel;
    private DAOGenerico dao = new DAOGenerico();
    private List<Leite> lista = new ArrayList<>();
    private Date dataInicial = new Date();
    private Date dataFinal = new Date();
    public String novaData, novaData2;
   
    public ChartView() {
    	createDateModel();
   
    	
   }
    
    @PostConstruct
    public void init() {
        createDateModel();
       
       
    }
 
    public LineChartModel getDateModel() {
        return dateModel;
    }
     public void pegarData() throws IOException{
    	 SimpleDateFormat formatador2 = new SimpleDateFormat("dd/MM/yyyy");
    	 FacesContext faces = FacesContext.getCurrentInstance();

    	 System.out.println(getDataInicial());
    	 
    	 SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd");
    	 novaData = formatador.format(dataInicial);
    	 novaData2 = formatador.format(dataFinal);
    	
    	 System.out.println(novaData+" nova data 2 " +novaData2);
//    	 lista = dao.lista(Leite.class);   
    	lista = dao.listaCondicao(Leite.class, "dataDoValor BETWEEN ' " + novaData + " ' AND ' " + novaData2 + " ' ");
 	  	System.out.println("no Preencher ELSE");
 	    for (Leite leite : lista) {
			System.out.println(leite.getDataDoValor());
		}
 	    if(lista.size()>=1){
 	 	   createDateModel();
 	 	  
 	 	   novaData = formatador2.format(dataInicial);
	       novaData2 = formatador2.format(dataFinal);
 	 	//  FacesContext.getCurrentInstance().getExternalContext().redirect("leiteGrafico.jsf");
 	     	}else{
 	     		
	 	      	 novaData = formatador2.format(dataInicial);
	 	      	 novaData2 = formatador2.format(dataFinal);
	 	     	 faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Nenhuma valor cadastrado entre a data "+novaData+" e a data "+novaData2));		
 	     	}
 	    }

    private void createDateModel() {
    	
    	FacesContext faces = FacesContext.getCurrentInstance();
    	 if(lista !=null){


    	    	
             dateModel = new LineChartModel();
             LineChartSeries series1 = new LineChartSeries();
             series1.setLabel("Series 1");

             for (Leite leite : lista) {
             	
             Object valor =	leite.getDataDoValor().getTime();
     			series1.set(valor, leite.getValorLitro());
     			dateModel.addSeries(series1);
     			
     			System.out.println(leite.getValorLitro());
     		}
      

          //   SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd");
            
 	      	 
              
             //dateModel.setTitle("Varia��o do Valor do litro do Leite");
             
             dateModel.getAxis(AxisType.Y).setLabel("Valor em R$");
             DateAxis axis = new DateAxis("Datas que ouve mudança no valor do Litro do Leite");
             axis.setTickAngle(0);
             axis.setMax(novaData2);
             axis.setTickCount(10);
          
             dateModel.getAxis(AxisType.Y).setTickCount(20);
             dateModel.getAxis(AxisType.Y).setMin(0);
             axis.setTickFormat("%#d, %b %y");
             
              
             dateModel.getAxes().put(AxisType.X, axis);
         	System.out.println("no GRAFICO");
     	
  	    		
  	    }else{
  	    	
  	   	faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação :","Removido!!!!"));
  	    }
     	
    }

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getNovaData() {
		return novaData;
	}

	public void setNovaData(String novaData) {
		this.novaData = novaData;
	}

	public String getNovaData2() {
		return novaData2;
	}

	public void setNovaData2(String novaData2) {
		this.novaData2 = novaData2;
	}
    
    
}