package com.socmed.socmed.modules.follow;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FollowCustomRepositoryImpl implements FollowCustomRepository{

    @Autowired
    private final EntityManager entityManager;

    @Override
    public List<MutualFollowersDTO> findMutualFollowersByUserIdAndFollowerId(Long userId, Long followerId) {
        String queryString = "SELECT pr.first_name, pr.last_name, pr.middle_name, pr.suffix, " +
                " COUNT(CASE WHEN f.user_id = :userId AND follower.id != :followerId THEN 1 ELSE NULL END)  " +
                "AS mutual " +
                "FROM follows f " +
                "JOIN users follower ON follower.id = f.follower_id " +
                "JOIN profiles pr ON pr.user_id = follower.id " +
                "WHERE follower.id = :followerId " +
                "GROUP BY pr.first_name, pr.last_name, pr.middle_name, pr.suffix, f.user_id";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("userId", userId);
        query.setParameter("followerId", followerId);

        List<Object[]> resultList = query.getResultList();

        return resultList.stream()
                .map(object -> new MutualFollowersDTO((String) object[0], (String) object[1],
                        (String) object[2], (String) object[3], (long) ((Long) object[4]).intValue()))
                .collect(Collectors.toList());
    }
}
