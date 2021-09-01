package com.example.demo.mapper;

import com.example.demo.entity.ShopOrderAssociationGoods;
import com.example.demo.entity.ShopOrderAssociationGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopOrderAssociationGoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association_goods
     *
     * @mbggenerated Tue May 11 15:55:11 CST 2021
     */
    int countByExample(ShopOrderAssociationGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association_goods
     *
     * @mbggenerated Tue May 11 15:55:11 CST 2021
     */
    int deleteByExample(ShopOrderAssociationGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association_goods
     *
     * @mbggenerated Tue May 11 15:55:11 CST 2021
     */
    int deleteByPrimaryKey(Long goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association_goods
     *
     * @mbggenerated Tue May 11 15:55:11 CST 2021
     */
    int insert(ShopOrderAssociationGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association_goods
     *
     * @mbggenerated Tue May 11 15:55:11 CST 2021
     */
    int insertSelective(ShopOrderAssociationGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association_goods
     *
     * @mbggenerated Tue May 11 15:55:11 CST 2021
     */
    List<ShopOrderAssociationGoods> selectByExample(ShopOrderAssociationGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association_goods
     *
     * @mbggenerated Tue May 11 15:55:11 CST 2021
     */
    ShopOrderAssociationGoods selectByPrimaryKey(Long goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association_goods
     *
     * @mbggenerated Tue May 11 15:55:11 CST 2021
     */
    int updateByExampleSelective(@Param("record") ShopOrderAssociationGoods record, @Param("example") ShopOrderAssociationGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association_goods
     *
     * @mbggenerated Tue May 11 15:55:11 CST 2021
     */
    int updateByExample(@Param("record") ShopOrderAssociationGoods record, @Param("example") ShopOrderAssociationGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association_goods
     *
     * @mbggenerated Tue May 11 15:55:11 CST 2021
     */
    int updateByPrimaryKeySelective(ShopOrderAssociationGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order_association_goods
     *
     * @mbggenerated Tue May 11 15:55:11 CST 2021
     */
    int updateByPrimaryKey(ShopOrderAssociationGoods record);
}