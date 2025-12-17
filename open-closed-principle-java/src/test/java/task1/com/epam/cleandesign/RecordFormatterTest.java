package task1.com.epam.cleandesign;

import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RecordFormatterTest {

    private final RecordFormatter formatter = new RecordFormatter();

    @Test
    public void formatAddress() {
        AddressRecord addressRecord = new AddressRecord();
        addressRecord.setId("1");
        addressRecord.setCountry("Ukraine");
        addressRecord.setProvince("Kharkivska");
        addressRecord.setCity("Kharkiv");
        addressRecord.setStreet("23 Serpnya");
        addressRecord.setBuilding("33");
        addressRecord.setApartment("N/A");
        addressRecord.setIndex("61000");
        String actualResult = formatter.format(addressRecord);
        assertThat(actualResult, is("address: Ukraine, Kharkivska, Kharkiv, 23 Serpnya st., 33 b., N/A apt., 61000"));
    }

    @Test
    public void formatBirthday() {
        BirthdayRecord birthdayRecord = new BirthdayRecord();
        birthdayRecord.setYear(1989);
        birthdayRecord.setMonth(11);
        birthdayRecord.setDay(27);
        String actualResult = formatter.format(birthdayRecord);
        assertThat(actualResult, is("birthday: 1989/11/27"));
    }

    @Test
    public void formatVisa() {
        VisaRecord visaRecord = new VisaRecord();
        visaRecord.setId("3");
        visaRecord.setCountry("Ukraine");
        visaRecord.setFrom(ZonedDateTime.of(2006, 7, 23, 0, 0, 0, 0, ZoneOffset.UTC));
        visaRecord.setTo(ZonedDateTime.of(2120, 7, 23, 0, 0, 0, 0, ZoneOffset.UTC));
        String actualResult = formatter.format(visaRecord);
        assertThat(actualResult, is("visa: Ukraine, from: 2006-07-23T00:00Z, to: 2120-07-23T00:00Z"));
    }

    @Test
    public void formatWorkload() {
        WorkloadRecord workloadRecord = new WorkloadRecord();
        workloadRecord.setId("4");
        Map<Integer, Double> workload = new LinkedHashMap<>();
        workload.put(1, 100d);
        workload.put(2, 90d);
        workload.put(3, 0d);
        workloadRecord.setWorkload(workload);
        String fmt = formatter.format(workloadRecord);
        assertThat(fmt, is("workload: 1:100.0, 2:90.0, 3:0.0"));
    }

    @Test
    public void formatSkills() {
        SkillsRecord skillsRecord = new SkillsRecord();
        skillsRecord.setSkill("Java");
        skillsRecord.setLevel(SkillsRecord.Level.INTERMEDIATE);
        String actualResult = formatter.format(skillsRecord);
        assertThat(actualResult, is("skills: Java=INTERMEDIATE"));
    }

    @Test
    public void formatFeedback() {
        FeedbackRecord feedbackRecord = new FeedbackRecord();
        feedbackRecord.setCriterion("Team work");
        feedbackRecord.setQuality(FeedbackRecord.Quality.MEETS_EXPECTATION);
        String actualResult = formatter.format( feedbackRecord);
        assertThat(actualResult, is("feedback: Team work=MEETS_EXPECTATION"));
    }
}
