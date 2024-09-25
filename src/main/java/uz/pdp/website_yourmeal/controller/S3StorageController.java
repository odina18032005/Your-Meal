package uz.pdp.website_yourmeal.controller;

//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import uz.pdp.website_yourmeal.service.S3StorageService;
//
//
//import java.io.IOException;
//import java.io.InputStream;
//
//@RestController
//@RequestMapping("/file")
//public class S3StorageController {
//
//
//    private final S3StorageService s3StorageService;
//
//    public S3StorageController(S3StorageService s3StorageService) {
//        this.s3StorageService = s3StorageService;
//    }
//
//    @PostMapping(value = "/upload",consumes = {"application/json", MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file){
//        s3StorageService.uploadToPublic(file);
//        return ResponseEntity.ok("File successfully uploaded");
//    }
//    @GetMapping("/download/{fileName}")
//    public ResponseEntity download(@PathVariable String fileName) throws IOException {
//        InputStream download = s3StorageService.download(fileName);
//        byte[] bytes = download.readAllBytes();
//        return ResponseEntity
//                .accepted()
//                .header("Content-Disposition", "attachment; filename=\""+fileName+"\"")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(bytes);
//    }
//
//}
