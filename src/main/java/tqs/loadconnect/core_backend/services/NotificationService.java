package tqs.loadconnect.core_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import tqs.loadconnect.core_backend.repositories.NotificationRepository;
import tqs.loadconnect.core_backend.models.Notification;

import java.util.List;
import java.util.Optional;

//@Service
//public class NotificationService {
//
//    @Autowired
//    private NotificationRepository notificationRepository;
//
//    public Notification createNotification(Notification notification) {
//        return notificationRepository.save(notification);
//    }
//
//    public List<Notification> getAllNotifications() {
//        return notificationRepository.findAll();
//    }
//
//    public Optional<Notification> getNotificationById(int notiID) {
//        return notificationRepository.findById(notiID);
//    }
//
//    public Optional<Notification> getAllNotificationsByUserName(String userName) {
//        return notificationRepository.findByUserName(userName);
//    }
//
//
//}
