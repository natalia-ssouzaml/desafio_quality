package com.example.desafio_quality.service;

import com.example.desafio_quality.dto.RoomDTO;
import com.example.desafio_quality.model.Property;
import java.math.BigDecimal;
import java.util.List;

public interface IProperty {

    /**
     * Método responsável por retornar a área total de uma propriedade recebida como parâmetro
     *
     * @param propertyName Nome da propriedade
     * @return double - Área total de uma propriedade
     */
    double getPropertyTotalM2(String propertyName);

    /**
     * Método responsável por retornar o preço total de uma propriedade recebida como parâmetro de acordo com o bairro
     *
     * @param propertyName Nome da propriedade
     * @return BigDecimal - Preço total de uma propriedade
     */
    BigDecimal getPropertyValue(String propertyName);

    /**
     * Método responsável por retornar o maior cômodo de uma propridade recebida como parâmetro
     *
     * @param propertyName Nome da propriedade
     * @return RoomDTO - Maior cômodo de uma propriedade
     */

    RoomDTO getBiggestRoom(String propertyName);

    /**
     * Método responsável por retornar a área total de cada cômodo de uma propriedade
     *
     * @param propertyName Nome da propriedade
     * @return List<RoomDTO> - Lista de cômodos de uma propriedade
     */
    List<RoomDTO> getTotalM2ByRoom(String propertyName);

    /**
     * Método responsável por criar uma nova propriedade
     *
     * @param property Recebe um objeto do tipo Property através do Body de uma requisição POST
     * @return Property - Propriedade que foi criada
     */
    Property createProperty(Property property);

}
