package com.example.demo88

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class ProductTest {

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Test
    fun test() {
        val category = categoryRepository.save(Category())
        productRepository.save(Product().apply { this.categories.add(category) })

        entityManager.flush()
        entityManager.clear()

        val actual = productRepository.findById(1L).get()

        println(actual.categories)
    }
}