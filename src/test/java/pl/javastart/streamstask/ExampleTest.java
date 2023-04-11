package pl.javastart.streamstask;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleTest {

    StreamsTask task = new StreamsTask();
    List<User> users = new ArrayList<>();
    List<Expense> expenses = new ArrayList<>();


    @Test
    public void shouldFindWoman() {
        //given
        users.add(new User(1L,"Monika", 18));
        users.add(new User(2L, "Andrzej", 40));
        //when
        Collection<User> women = task.findWomen(users);
        //then
        assertThat(women.size()).isEqualTo(1);
    }

    @Test
    public void shouldNotFindWoman() {
        //given
        users.add(new User(1L,"Jan", 18));
        users.add(new User(2L, "Andrzej", 40));
        //when
        Collection<User> women = task.findWomen(users);
        //then
        assertThat(women.size()).isEqualTo(0);
    }

    @Test
    public void shouldFindAverageAgeForMan() {
        //given
        users.add(new User(1L,"Jan", 18));
        users.add(new User(2L, "Andrzej", 40));
        users.add(new User(3L,"Monika", 18));
        //when
        Double averageMenAge = task.averageMenAge(users);
        //then
        assertThat(averageMenAge).isEqualTo(29);
    }

    @Test
    public void shouldNotFindAverageAgeForMan() {
        //given
        users.add(new User(1L,"Anna", 18));
        users.add(new User(2L, "Kinga", 40));
        users.add(new User(3L,"Monika", 18));
        //when
        Double averageMenAge = task.averageMenAge(users);
        //then
        assertThat(averageMenAge).isEqualTo(0);
    }

    @Test
    public void shouldFindExpensesForUserId() {
        //given
        users.add(new User(1L,"Anna", 18));
        users.add(new User(2L, "Kinga", 40));
        expenses.add(new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR));
        expenses.add(new Expense(1L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD));
        expenses.add(new Expense(2L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Skarpetki", new BigDecimal("39"), ExpenseType.WEAR));
        //when
        Map<Long, List<Expense>> longListMap = task.groupExpensesByUserId(users, expenses);
        //then
        assertThat(longListMap.size()).isEqualTo(2);
        assertThat(longListMap.get(1L).size()).isEqualTo(2);
        assertThat(longListMap.get(2L).size()).isEqualTo(2);
    }

    @Test
    public void shouldFindExpensesForUserName() {
        //given
        users.add(new User(1L,"Anna", 18));
        users.add(new User(2L, "Kinga", 40));
        expenses.add(new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR));
        expenses.add(new Expense(1L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD));
        expenses.add(new Expense(2L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Skarpetki", new BigDecimal("39"), ExpenseType.WEAR));
        //when
        Map<User, List<Expense>> userListMap = task.groupExpensesByUser(users, expenses);
        //then
        assertThat(userListMap.size()).isEqualTo(2);
        assertThat(userListMap.get(users.get(0)).size()).isEqualTo(2);
        assertThat(userListMap.get(users.get(1)).size()).isEqualTo(2);
    }


}
