package com.example.sport_api.unit.repositories.sport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.sport_api.models.sport.Membership;
import com.example.sport_api.repositories.soccer.MembershipRepository;

@DataJpaTest
public class MembershipRepositryTests {

    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    public void testMembershipMapping() {

        // Create a new Membership object with sample data
        Membership membership = new Membership();
        membership.setMembershipId(1);
        membership.setTeamName("test");

        // Save the Membership object to the repository
        membershipRepository.save(membership);

        // Retrieve the Membership object from the repository using its ID
        // and assign it to the optionalMembership variable
        Optional<Membership> optionalMembership = membershipRepository.findById(1);

        assertTrue(optionalMembership.isPresent());

        Membership savedMembership = optionalMembership.get();

        // Perform assertions to verify that the retrieved data matches the expected
        // values
        assertEquals(1, savedMembership.getMembershipId());
        assertEquals("test", savedMembership.getTeamName());

    }

}
