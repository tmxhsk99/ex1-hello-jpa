package hello.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Embeddable;

@Embeddable
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Period() {
    }

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Embeded 타입을 활용하면 이런식으로 코드를 더 응집성있게 작성할 수 있다.
    public boolean isWork(){
        boolean result = false;
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(this.startDate) && now.isBefore(this.endDate)){
            result = true;
        }
        return result;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }


    public LocalDateTime getEndDate() {
        return endDate;
    }

}
