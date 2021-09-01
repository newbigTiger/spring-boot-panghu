package com.example.demo.mapper;

import com.example.demo.entity.ShopOrderAssociation;
import com.example.demo.entity.ShopOrderAssociationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopOrderAssociationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association
     *
     * @mbggenerated Tue May 11 15:54:47 CST 2021
     */
    int countByExample(ShopOrderAssociationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association
     *
     * @mbggenerated Tue May 11 15:54:47 CST 2021
     */
    int deleteByExample(ShopOrderAssociationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association
     *
     * @mbggenerated Tue May 11 15:54:47 CST 2021
     */
    int deleteByPrimaryKey(Long associationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association
     *
     * @mbggenerated Tue May 11 15:54:47 CST 2021
     */
    int insert(ShopOrderAssociation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association
     *
     * @mbggenerated Tue May 11 15:54:47 CST 2021
     */
    int insertSelective(ShopOrderAssociation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association
     *
     * @mbggenerated Tue May 11 15:54:47 CST 2021
     */
    List<ShopOrderAssociation> selectByExample(ShopOrderAssociationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association
     *
     * @mbggenerated Tue May 11 15:54:47 CST 2021
     */
    ShopOrderAssociation selectByPrimaryKey(Long associationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association
     *
     * @mbggenerated Tue May 11 15:54:47 CST 2021
     */
    int updateByExampleSelective(@Param("record") ShopOrderAssociation record, @Param("example") ShopOrderAssociationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association
     *
     * @mbggenerated Tue May 11 15:54:47 CST 2021
     */
    int updateByExample(@Param("record") ShopOrderAssociation record, @Param("example") ShopOrderAssociationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association
     *
     * @mbggenerated Tue May 11 15:54:47 CST 2021
     */
    int updateByPrimaryKeySelective(ShopOrderAssociation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association
     *
     * @mbggenerated Tue May 11 15:54:47 CST 2021
     */
    int updateByPrimaryKey(ShopOrderAssociation record);
}