package com.example.sport_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sport_api.models.Membership;
import com.example.sport_api.repositories.MembershipRepository;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    public List<Membership> getMembershipsByCompetition(Integer Competition) {

        List<Membership> memberships = membershipRepository.findByCompetitionId(Competition);

        return memberships;

    }

}
