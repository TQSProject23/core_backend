package tqs.loadconnect.core_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.loadconnect.core_backend.models.Notification;
//import tqs.loadconnect.core_backend.services.NotificationService;

import java.util.List;
import java.util.Optional;


//@RestController
//@CrossOrigin(origins="*")
//@RequestMapping("/api/v1/notifications")
//public class NotificationController {
//
//    @Autowired
//    private NotificationService notificationService;
//
//    // add a new notification
//    @PostMapping("/new")
//    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
//        System.out.println("createNotification: " + notification.toString());
//        Notification newNotification = notificationService.createNotification(notification);
//        if (newNotification == null) {
//            return ResponseEntity.badRequest().build();
//        } else {
//            return ResponseEntity.ok().body(newNotification);
//        }
//    }
//
//    // get all notifications
//    @GetMapping("/all")
//    public ResponseEntity<List<Notification>> getAllNotifications() {
//        List<Notification> notifications = notificationService.getAllNotifications();
//        return ResponseEntity.ok().body(notifications);
//    }
//
//    // get notification by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Notification>> getNotificationById(@PathVariable(value="id") Integer notiID) {
//        Optional<Notification> notification = notificationService.getNotificationById(notiID);
//        if (notification.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok().body(notification);
//        }
//    }
//
//    // get notifications by username
//    @GetMapping("/user/{user_name}")
//    public ResponseEntity<Optional<Notification>> getNotificationByUserName(@PathVariable(value="user_name") String userName) {
//        Optional<Notification> notification = notificationService.getAllNotificationsByUserName(userName);
//        if (notification.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok().body(notification);
//        }
//    }
//
//    // delete notification by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Optional<Notification>> deleteNotificationById(@PathVariable(value="id") Integer notiID) {
//        // TODO: implement this
//        return ResponseEntity.notFound().build();
//    }
//
//}
//