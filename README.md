# Product API Solutions

This repository contains solutions to two coding tasks involving interacting with the [Fake Store API](https://fakestoreapi.com/) to retrieve and add product information based on specific requirements. The tasks are implemented as separate API endpoints in [your chosen language/server].

---

## Table of Contents

- [Task 1: Retrieve Products by Category](#task-1-retrieve-products-by-category)
- [Task 2: Add a New Product](#task-2-add-a-new-product)
- [Usage Instructions](#usage-instructions)
- [Error Handling and Security Considerations](#error-handling-and-security-considerations)

---

## Task 1: Retrieve Products by Category

**Endpoint:** `GET /api/products?category=<category_name>`

This endpoint fetches and lists product details (such as title, price, and description) based on the `category` parameter provided in the query string. It retrieves data from the Fake Store API’s `/products/category/<category_name>` endpoint and returns a structured JSON response containing only the required fields.

## Task 2: Add a New Product
Endpoint: POST /api/products

This endpoint allows the addition of a new product with all relevant properties, posting the data to the Fake Store API’s /products endpoint. The request body should include product details such as title, price, description, etc.




