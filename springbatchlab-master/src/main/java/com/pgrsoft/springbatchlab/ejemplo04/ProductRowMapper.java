package com.pgrsoft.springbatchlab.ejemplo04;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Product product = new Product();
		
		product.setCodigo(rs.getInt("codigo"));
		product.setNombre(rs.getString(2) + "_" + rowNum); // se puede utilizar el número de columna
		product.setPrecio(rs.getDouble("precio"));
		product.setFamilia(rs.getString("familia"));
		
		return product;
	}
}
