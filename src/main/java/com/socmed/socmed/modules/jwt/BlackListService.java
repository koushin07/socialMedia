package com.socmed.socmed.modules.jwt;


import org.springframework.stereotype.Service;

@Service
public record BlackListService(
        BlackListRepository blackListRepository
) {

    public Boolean isAccessTokenBlackListed(String accessToken){
        return blackListRepository.findById(accessToken).isPresent();
    }

    public void blackListToken(String accessToken) {
        blackListRepository.save(BlackList.builder()
                .accessToken(accessToken).build());
    }
}
