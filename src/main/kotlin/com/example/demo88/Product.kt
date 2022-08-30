package com.example.demo88

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "products_categories")
    var categories: MutableList<Category> = mutableListOf()
)

@Entity
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToMany(mappedBy = "categories")
    val products: List<Product> = emptyList()
)

interface ProductRepository: JpaRepository<Product, Long>
interface CategoryRepository: JpaRepository<Category, Long>