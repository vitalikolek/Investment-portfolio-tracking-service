package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.SearchUserDTO;
import org.geekhub.vitalii.repository.SearchUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchUserService {

    private final SearchUserRepository searchUserRepository;

    @Autowired
    public SearchUserService(SearchUserRepository searchUserRepository) {
        this.searchUserRepository = searchUserRepository;
    }

    public List<SearchUserDTO> findUser(String username) {
        return searchUserRepository.findUser(username);
    }
}
