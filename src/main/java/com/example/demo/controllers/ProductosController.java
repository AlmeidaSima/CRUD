package com.example.demo.controllers;

import java.io.IOException;
import java.io.InputStream;

import java.nio.file.*;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Producto;
import com.example.demo.models.ProductoDto;
import com.example.demo.services.ProductosRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/productos")
public class ProductosController {
	
	
	@Autowired
	private ProductosRepository repo;
	
	@GetMapping({"","/"})
	public String ListaDeProdutos(Model model) {
		List<Producto> productos = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("productos", productos);
		return "index";		
	}
	@GetMapping("/cuadricula")
    public String vistaCuadriculada(Model model) {
		List<Producto> productos = repo.findAll();
		model.addAttribute("productos", productos);
		return "vista/vistaCuadriculada";	
    }
	
	@GetMapping("/create")
	public String ShowCreatePage(Model model) {
		ProductoDto productoDto = new ProductoDto();
		model.addAttribute("productoDto", productoDto);
		return "productos/crearProducto";
	}
	
	@PostMapping("/create")
	public String crearProductos(
			@Valid @ModelAttribute ProductoDto productoDto,
			BindingResult result
			) {
		
		if(productoDto.getImageFile().isEmpty()) {
			result.addError(new FieldError("productoDto", "imageFile", "no cargo we"));
		}
		if(result.hasErrors()) {
			return "productos/crearProducto";
		}
		
		MultipartFile image = productoDto.getImageFile();
		Date createdAt = new Date();
		String storageFileName = createdAt.getTime() + "__" + image.getOriginalFilename();
		
		try {
			String uploadDir = "public/images/";
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try (InputStream inputStream = image.getInputStream()){
				Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
						StandardCopyOption.REPLACE_EXISTING);
				
			}
		} catch (Exception ex) {
			System.out.print("Exception" + ex.getMessage());
		}
		
		Producto producto = new Producto();
		producto.setName(productoDto.getName());
		producto.setBrand(productoDto.getBrand());
		producto.setCategory(productoDto.getCategory());
		producto.setPrice(productoDto.getPrice());
		producto.setDescription(productoDto.getDescription());
		producto.setCreatedAt(createdAt);
		producto.setImageFileName(storageFileName);
		
		repo.save(producto);
		
		return "redirect:/productos";
	}
	
	@GetMapping("/edit")
	public String ShowViewEditar(Model model, 
			@RequestParam int id)  {

		try {
			Producto producto = repo.findById(id).get();
			model.addAttribute("producto", producto);
			
			ProductoDto productoDto = new ProductoDto();
			productoDto.setName(producto.getName());
			productoDto.setBrand(producto.getBrand());
			productoDto.setCategory(producto.getCategory());
			productoDto.setPrice(producto.getPrice());
			productoDto.setDescription(producto.getDescription());
			
			
			model.addAttribute("productoDto", productoDto);
			
		} catch (Exception ex) {
			System.out.println("Exception: "+ ex.getMessage());
			return "redirect:/productos";
		
		} 
		return "productos/EditarProducto";
	}
	
	@PostMapping("/edit")
	public String updateProduct(
	        Model model,
	        @RequestParam("id") int id,
	        @Valid @ModelAttribute ProductoDto productoDto,
	        BindingResult result
	) {
	    try {
	        Producto producto = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

	        model.addAttribute("producto", producto);

	        if (result.hasErrors()) {
	            return "productos/EditarProducto";
	        }

	        if (!productoDto.getImageFile().isEmpty()) {
	            String uploadDir = "public/images/";
	            Path oldImagePath = Paths.get(uploadDir, producto.getImageFileName());

	            try {
	                Files.deleteIfExists(oldImagePath);
	            } catch (IOException ex) {
	                System.err.println("No se pudo eliminar la imagen anterior: " + ex.getMessage());
	            }

	            MultipartFile image = productoDto.getImageFile();
	            String contentType = image.getContentType();
	            if (contentType == null || !contentType.startsWith("image/")) {
	                model.addAttribute("error", "Solo se permiten archivos de imagen.");
	                return "productos/EditarProducto";
	            }

	            String storageFileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();

	            try (InputStream inputStream = image.getInputStream()) {
	                Files.copy(inputStream, Paths.get(uploadDir, storageFileName),
	                        StandardCopyOption.REPLACE_EXISTING);
	            }

	            producto.setImageFileName(storageFileName);
	        }

	        producto.setName(productoDto.getName());
	        producto.setBrand(productoDto.getBrand());
	        producto.setCategory(productoDto.getCategory());
	        producto.setPrice(productoDto.getPrice());
	        producto.setDescription(productoDto.getDescription());

	        repo.save(producto);

	    } catch (Exception ex) {
	        System.err.println("Error al actualizar el producto: " + ex.getMessage());
	    }

	    return "redirect:/productos";
	}

	@GetMapping("/delete")
	public String deleteProducto(@RequestParam("id") int id) {
	    try {
	        Producto producto = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
	        Path imagePath = Paths.get("public", "images", producto.getImageFileName());

	        try {
	            Files.deleteIfExists(imagePath);
	        } catch (IOException ex) {
	            System.err.println("No se pudo eliminar la imagen: " + ex.getMessage());
	        }

	        repo.delete(producto);
	        System.out.println("Producto eliminado exitosamente: " + id);

	    } catch (Exception ex) {
	        System.err.println("Error al eliminar el producto: " + ex.getMessage());
	    }

	    return "redirect:/productos";
	}

	
	

	
}
