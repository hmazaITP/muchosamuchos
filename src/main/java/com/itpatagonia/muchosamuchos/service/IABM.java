package com.itpatagonia.muchosamuchos.service;

import java.util.List;
import java.util.Optional;

public interface IABM {
    public Optional<?> create(Optional<?> optional);
    public List<?> findAll();
    public Optional<?> findById(Long id);
    public Optional<?> update(Long id, Optional<?> optional);
    public Boolean delete(Long id);


}
