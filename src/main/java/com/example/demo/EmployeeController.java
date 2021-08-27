package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	private JwtUtil jwt;

	@Autowired
	private EmployeeService service;

	@Autowired
	private AuthenticationManager authManager;

	@PostMapping("/token/login")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest auth) throws Exception {

		// System.out.println(auth.getUserName());
		try {

			if (auth != null && auth.getUserName() != null && auth.getPassword() != null) {
				authManager
						.authenticate(new UsernamePasswordAuthenticationToken(auth.getUserName(), auth.getPassword()));
				String jwtToken = jwt.generateToken(auth.getUserName());
				if (jwtToken != null) {
					return ResponseEntity.ok(new AuthResponse(jwtToken, auth.getUserName()));
				} else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
//			throw new Exception();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		}
		return null;
	}

	@PostMapping("/register")
	public ResponseEntity<?> generateRegisterToken(@RequestBody Employee emp) throws Exception {
		try {
			
			
			service.addEmployee(emp);
			
			String jwtToken = jwt.generateToken(emp.getUsername());
			if (jwtToken != null) {
				return ResponseEntity.ok(new AuthResponse(jwtToken, emp.getUsername()));
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}

	@GetMapping("/employee")
	public ResponseEntity<?> getEmployees() {
		try {
			List<EmployeeData> employee = service.getEmployees();
			
			return new ResponseEntity<List<EmployeeData>>(employee, HttpStatus.OK);
		} catch (BuisnessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode());
			if (e.getErrorCode() == httpStatusCode.not_found) {
				return new ResponseEntity<ControllerException>(ce, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			ControllerException ce = new ControllerException(httpStatusCode.internal_server_error);
			return new ResponseEntity<ControllerException>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;

	}

	@GetMapping("/employee/{id}")
	public Optional<EmployeeData> getEmployee(@PathVariable String id) {
		
		return service.getEmployee(id) ;
		
	}

	@GetMapping("/employeeLogin/{id}")
	public Optional<Employee> loginEmployee(@PathVariable String id) {
		return service.loginEmployee(id);
	}

	@PostMapping("/employee")
	public ResponseEntity<?> postEmployee(@RequestBody EmployeeData emp) {

		try {
			EmployeeData employee = service.addEmployeeData(emp);
			return new ResponseEntity<EmployeeData>(employee, HttpStatus.OK);
		} catch (BuisnessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode());
			if (ce.getErrorCode() == httpStatusCode.bad_request) {
				return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
			} else if (ce.getErrorCode() == httpStatusCode.forbidden) {
				return new ResponseEntity<ControllerException>(ce, HttpStatus.FORBIDDEN);
			} else {
				return null;
			}

		} catch (Exception e) {
			ControllerException ce = new ControllerException(httpStatusCode.internal_server_error);
			return new ResponseEntity<ControllerException>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<?> updateEmployeeData(@PathVariable("id") String id,@RequestBody EmployeeData emp){
		try {
			EmployeeData employee = service.updateEmployeeData(emp);
			return new ResponseEntity<EmployeeData>(employee, HttpStatus.OK);
		} catch (BuisnessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode());
			if (ce.getErrorCode() == httpStatusCode.bad_request) {
				return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
			} else if (ce.getErrorCode() == httpStatusCode.forbidden) {
				return new ResponseEntity<ControllerException>(ce, HttpStatus.FORBIDDEN);
			} else {
				return null;
			}

		} catch (Exception e) {
			ControllerException ce = new ControllerException(httpStatusCode.internal_server_error);
			return new ResponseEntity<ControllerException>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/employee/{id}")
	public void deleteEmplyee(@PathVariable("id") String id) {
		service.deleteEmployee(id);
	}

	@Autowired
	ImageRepository imageRepository;

	@PostMapping("/upload/{id}")
	public BodyBuilder uplaodImage(@PathVariable("id") String id, @RequestParam("imageFile") MultipartFile file)
			throws IOException {

		System.out.println("Original Image Byte Size - " + file.getBytes().length);

		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));
		imageRepository.save(img);

		return ResponseEntity.status(HttpStatus.OK);

	}

	@GetMapping("/get/{id}")
	public ImageModel getImage(@PathVariable("id") String id) throws IOException {

		final Optional<ImageModel> retrievedImage = imageRepository.findById(id);
		ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
				decompressBytes(retrievedImage.get().getPicByte()));

		return img;

	}

	public static byte[] compressBytes(byte[] data) {

		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];

		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();

		} catch (IOException e) {

		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();

	}

	public static byte[] decompressBytes(byte[] data) {

		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];

		try {

			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {

		} catch (DataFormatException e) {

		}
		return outputStream.toByteArray();

	}

}
