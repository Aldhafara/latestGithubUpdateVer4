package pl.noCompany.latestGithubUpdateVer4.logic;

import org.junit.Test;
import pl.noCompany.latestGithubUpdateVer4.model.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LogicTest {

    Logic logic = new Logic();

    @Test
    public void shouldReturnCorrectDateFromGivenString(){
        //given
        String date = "2019-08-25T23:32:26Z";
        //when
        LocalDateTime newDate = Logic.dateFormatter(date);
        //then
        assertThat(newDate).isEqualTo(LocalDateTime.of(2019,8,26,1,32,26));
    }

    @Test
    public void shouldSortTwoListsAndJoinThemTogether(){
        //given
        LocalDateTime date = LocalDateTime.of(2019,8,26,1,32,26);

        List<Repository> firstList = new ArrayList<>();
        List<Repository> secondList = new ArrayList<>();

        Repository r1 = new Repository("ZzzzAAaa",null);
        Repository r21 = new Repository("c",date.plusHours(3));
        Repository r22 = new Repository("g",date.plusHours(2));
        Repository r23 = new Repository("a",date.plusHours(23));
        Repository r24 = new Repository("z",date);
        Repository r25 = new Repository("b",date.plusHours(5));

        //when

        firstList.add(r1);
        secondList.add(r21);
        secondList.add(r22);
        secondList.add(r23);
        secondList.add(r24);
        secondList.add(r25);

        List<Repository> joinedList = Logic.sortAndJoin(firstList,secondList);

        //then
        assertThat(joinedList).containsExactly(r1, r23, r25, r21, r22, r24);
        assertThat(joinedList.size()).isEqualTo(firstList.size()+secondList.size());
    }

    @Test
    public void shouldReturn200ForAldhafara(){
        //given
        String name = "Aldhafara";
        int responseCode;
        List<Repository> list = new ArrayList<>();
        //when
        logic.connection(name,list);
        responseCode=Logic.responseCode;

        //then
        assertThat(responseCode).isEqualTo(200);
    }

}