package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.csvreader.CsvReader;

import dominio.Empresa;
import dominio.DAO.DataBase;
import dominio.DAO.EmpresaDAO;

public class EmpresaControlador {	
	
	private EmpresaDAO empresaDAO = new EmpresaDAO();
	
	public Empresa registrarEmpresa(String codigo, String razon_social, String rubro, String direccion, String correo, String telefono, String ciudad, 
    		String pais, String estado, String observaciones){
		Empresa proveedor = new Empresa(codigo, razon_social, rubro, direccion, correo, telefono, ciudad, pais, estado, observaciones);		
		empresaDAO.crearEmpresa(proveedor);
		return proveedor;
	}
	
public void importarEmpresas(String nombreArchivo){
		
		try {
			
			CsvReader empresa = new CsvReader(nombreArchivo);
		
			empresa.readHeaders();

			while (empresa.readRecord()){
			
				String codigo = empresa.get(0);				
				String razonSocial = empresa.get(1);				
				String rubro = empresa.get(2);
				String direccion = empresa.get(3);
				String correo = empresa.get(4);				
				String telefono = empresa.get(5);				
				String ciudad = empresa.get(6);				
				String pais = empresa.get(7);
				String estado = empresa.get(8);
				String observaciones = empresa.get(9);				
			
				registrarEmpresa(codigo, razonSocial, rubro, direccion, correo, telefono, 
						ciudad, pais, estado, observaciones);
			}
	
			empresa.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Empresa> devolverListaEmpresas()
	{
	    return empresaDAO.devolverEmpresa();
	}
	
	public int cantidadEmpresas(){
        return empresaDAO.cantidadEmpresas();
    }
	
	public void setDataBase(DataBase bdMock) {
        this.empresaDAO.setBaseDatosMock(bdMock);
    }
	
	public void limpiaListaEmpresa() {
		this.empresaDAO.limpiaListaEmpresa();
    }

}
