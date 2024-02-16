package com.shopeeClone.shopeeClone.service.impl;

import com.shopeeClone.shopeeClone.converter.RateConverter;
import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.rate.CreateRateDTO;
import com.shopeeClone.shopeeClone.dto.rate.RateDTO;
import com.shopeeClone.shopeeClone.entity.ProductEntity;
import com.shopeeClone.shopeeClone.entity.RateEntity;
import com.shopeeClone.shopeeClone.entity.UserEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.ProductRepository;
import com.shopeeClone.shopeeClone.repository.RateRepository;
import com.shopeeClone.shopeeClone.repository.UserRepository;
import com.shopeeClone.shopeeClone.service.RateService;
import com.shopeeClone.shopeeClone.utils.AppStringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RateServiceImpl implements RateService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private RateConverter rateConverter;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public RateDTO createRate(CreateRateDTO createRateDTO) {
        if (createRateDTO.getId()!=null){
            UserEntity userEntity= userRepository.findById(createRateDTO.getId()).orElseThrow(()-> new ValidateException("User not found"));
            createRateDTO.setName(userEntity.getUsername());
            createRateDTO.setTelephone(userEntity.getPhoneNumber());
            RateEntity rateEntity = rateConverter.toEntity(createRateDTO);
            rateRepository.save(rateEntity);
            return rateConverter.toDto(rateEntity);
        }
//        if (createRateDTO.getId()==null){
//            RateEntity rateEntity = rateConverter.toEntity(createRateDTO);
//            Calendar cal = Calendar.getInstance();
//            Date date = cal.getTime();
//            rateEntity.setCreateBy("null");
//            rateEntity.setCreateDate(date);
//            rateEntity.setModifierBy("null");
//            rateEntity.setModifierDate(date);
//            String insertHql = "INSERT INTO RateEntity (name, telephone, star, feedBack, createDate, modifierDate, createBy, modifierBy, entity) " +
//                    "VALUES (:name, :telephone, :star, :feedBack, :createDate, :modifierDate, :createBy, :modifierBy, :entity)";
//            Query selectQuery = entityManager.createQuery(insertHql);
//            selectQuery.setParameter("name", rateEntity.getName());
//            selectQuery.setParameter("telephone", rateEntity.getTelephone());
//            selectQuery.setParameter("star", rateEntity.getStar());
//            selectQuery.setParameter("feedBack", rateEntity.getFeedBack());
//            selectQuery.setParameter("createDate", rateEntity.getCreateDate());
//            selectQuery.setParameter("modifierDate", rateEntity.getModifierDate());
//            selectQuery.setParameter("createBy", rateEntity.getCreateBy());
//            selectQuery.setParameter("modifierBy", rateEntity.getModifierBy());
//            selectQuery.setParameter("entity", rateEntity.getEntity());
//            selectQuery.executeUpdate();
//            return rateConverter.toDto(rateEntity);
//        }
        return null;
    }

    @Override
    public List<RateDTO> getFeedBack(Long productId) {
        List<RateEntity> rateEntities = rateRepository.findByProductId(productId);
        return rateConverter.rateDTOList(rateEntities);
    }

    @Override
    public void deleteFeedback(Long id) {
        RateEntity rateEntity = rateRepository.findById(id).orElseThrow(()-> new ValidateException("FeedBack not found"));
        rateRepository.delete(rateEntity);
    }

//    @Override
//    public List<RateDTO> getAll() {
//        List<RateEntity> rateEntities = rateRepository.findAll();
//        return rateConverter.rateDTOList(rateEntities);
//    }

    @Override
    public PageDTO<RateDTO> getRate(Map<String, String> params) {
        String pageStr = params.get("page");
        String limitStr = params.get("limit");

        Integer page =1;
        Integer limit = 10;
        if(AppStringUtils.hasTextAnd(pageStr)){
            page = Integer.valueOf(pageStr);
        }
        if(AppStringUtils.hasTextAnd(limitStr)){
            limit = Integer.valueOf(limitStr);
        }

        StringBuilder selectQueryBuilder = new StringBuilder("select r from RateEntity r ");
        StringBuilder countQueryBuilder = new StringBuilder("select count(r.id) from RateEntity r ");

        String username = params.get("username");
        if(AppStringUtils.hasTextAnd(username)){
            selectQueryBuilder.append(" where r.name like :username");
            countQueryBuilder.append(" where r.name like :username");
        }

        TypedQuery<RateEntity> selectQuery = entityManager.createQuery(selectQueryBuilder.toString(), RateEntity.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(countQueryBuilder.toString(), Long.class);

        if(AppStringUtils.hasTextAnd(username)){
            selectQuery.setParameter("username", "%"+username+"%");
            countQuery.setParameter("username", "%"+username+"%");
        }

        Integer firstItem = (page-1) * limit;

        selectQuery.setFirstResult(firstItem);
        selectQuery.setMaxResults(limit);

        List<RateEntity> rateEntities = selectQuery.getResultList();
        List<RateDTO> dtos = rateConverter.rateDTOList(rateEntities);

        Long count = countQuery.getSingleResult();
        return new PageDTO<>(page,limit,count,dtos);
    }

    @Override
    public PageDTO<RateDTO> getRatesByProductId(Map<String, String> params, Long productId) {
        String pageStr = params.get("page");
        String limitStr = params.get("limit");

        Integer page =1;
        Integer limit = 10;
        if(AppStringUtils.hasTextAnd(pageStr)){
            page = Integer.valueOf(pageStr);
        }
        if(AppStringUtils.hasTextAnd(limitStr)){
            limit = Integer.valueOf(limitStr);
        }

        StringBuilder selectQueryBuilder = new StringBuilder("select r from  RateEntity r where r.entity.productId = :productId");
        StringBuilder countQueryBuilder = new StringBuilder("select count(r.id) from RateEntity r where r.entity.productId = :productId");

        TypedQuery<RateEntity> selectQuery = entityManager.createQuery(selectQueryBuilder.toString(), RateEntity.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(countQueryBuilder.toString(), Long.class);

            selectQuery.setParameter("productId", productId);
            countQuery.setParameter("productId", productId);

        Integer firstItem = (page-1) * limit;

        selectQuery.setFirstResult(firstItem);
        selectQuery.setMaxResults(limit);

        List<RateEntity> rateEntities = selectQuery.getResultList();
        List<RateDTO> dtos = rateConverter.rateDTOList(rateEntities);

        Long count = countQuery.getSingleResult();
        return new PageDTO<>(page,limit,count,dtos);
    }
}
