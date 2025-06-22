package com.example.moviebooking.feature;

import com.example.moviebooking.entity.ShowSeat;
import com.example.moviebooking.repository.ShowSeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Component
public class LockCleaner {

    private final ShowSeatRepository showSeatRepository;
    private static final long LOCK_EXPIRY_MILLIS = 5 * 60 * 1000;

    @Transactional
    @Scheduled(fixedRate = 180_000)
    public void lockCleaner(){

        long now = System.currentTimeMillis();

       List<ShowSeat> showSeats = showSeatRepository.findByIsLockedTrue();
       for (ShowSeat seat:showSeats){
           if (seat.getLockedAt()!=null && now- seat.getLockedAt()>LOCK_EXPIRY_MILLIS){
               seat.setLockedAt(null);
               seat.setLockedBy(null);
               seat.setLocked(false);
               System.out.println("Remove Lock Automatically From "+seat.getId());
           }
       }
       showSeatRepository.saveAll(showSeats);
    }

}
