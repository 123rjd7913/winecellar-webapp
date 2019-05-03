package com.cellar.wine.services;

import com.cellar.wine.models.Producer;

public interface ProducerService extends CrudService<Producer, Long> {

    Producer findByName(String name);

}
