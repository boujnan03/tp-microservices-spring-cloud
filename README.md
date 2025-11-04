#  TP Microservices - Architecture Spring Cloud Complète

##  Contexte
**TP Microservices - Architecture Spring Cloud Complète**  
*Architecture Microservices complète avec Spring Cloud *

##  Fonctionnalités Implémentées

###  Architecture & Infrastructure
- **8 Microservices** Spring Boot interconnectés
- **Service Discovery** avec Netflix Eureka
- **API Gateway** avec Spring Cloud Gateway  
- **Configuration Centralisée** avec Spring Cloud Config
- **Load Balancing** automatique avec multiples instances

###  Sécurité & Authentification
- **Gateway Security** avec filtres d'authentification
- **Service d'Authorization** dédié
- **Système de Rôles** (USER - lecture seule, ADMIN - accès complet)
- **Validation des headers** de sécurité

###  Résilience & Tolerance aux Pannes
- **Circuit Breaker** avec Resilience4j
- **Fallback Methods** pour dégradation gracieuse
- **Health Checks** avec Spring Actuator
- **Métriques custom** pour monitoring

###  Communication & Data
- **REST APIs** entre microservices
- **Feign Clients** pour communication inter-services
- **Validation des données** (weight 0-100kg, rate 0-100%)
- **Aggrégation de données** via Service Composite

##  Architecture du Système

Client 

 Gateway (8765)   Sécurité & Routing  (Spring Cloud Gateway) 



 Product-Composite (8070)   Circuit Breaker & Aggregation  (Service Composite) 

  
  
 Product   Review   Recommendation  Multi-instances  (8081)   (8086)   (8083/8084) 
  

##  Services Déployés

| Service | Port | Statut | Description |
|---------|------|--------|-------------|
| Eureka Server | 8761 |  | Service Discovery |
| Config Server | 8091 |  | Configuration Centralisée |
| Gateway | 8765 |  | Routing & Sécurité |
| Authorization | 8085 |  | Authentification |
| Product | 8081 |  | Gestion des produits |
| Review | 8086 |  | Gestion des avis |
| Recommendation | 8083,8084 |  | Gestion des recommandations |
| Product-Composite | 8070 |  | Agrégation & Circuit Breaker |

##  Tests de Fonctionnement

###  Sécurité & Authentification
```bash
# Accès refusé sans authentification (401)
curl http://localhost:8765/api/composite/1

# Mauvais credentials (403)
curl -H "username: wrong" -H "password: wrong" -H "role: USER" http://localhost:8765/api/composite/1

# Authentification USER réussie (200)
curl -H "username: user" -H "password: user123" -H "role: USER" http://localhost:8765/api/composite/1

# Authentification ADMIN réussie (200)
curl -H "username: admin" -H "password: admin123" -H "role: ADMIN" http://localhost:8765/api/composite/1
```

###  Circuit Breaker & Résilience
```bash
# Mode normal - Succès
curl -H "username: user" -H "password: user123" -H "role: USER" "http://localhost:8765/api/composite/test/resilience?fail=false"

# Mode erreur - Fallback activé
curl -H "username: user" -H "password: user123" -H "role: USER" "http://localhost:8765/api/composite/test/resilience?fail=true"

# Health Check
curl -H "username: user" -H "password: user123" -H "role: USER" http://localhost:8765/api/composite/health
```

###  Création & Validation
```bash
# ADMIN crée produit valide
curl -H "username: admin" -H "password: admin123" -H "role: ADMIN" -X POST http://localhost:8765/api/composite/ -H "Content-Type: application/json" -d "{"productId":100, "name":"Test Product", "weight":50}"

# Validation weight invalide (400)
curl -H "username: admin" -H "password: admin123" -H "role: ADMIN" -X POST http://localhost:8765/api/composite/ -H "Content-Type: application/json" -d "{"productId":101, "name":"Invalid Product", "weight":150}"

# USER tente création (403 - Interdit)
curl -H "username: user" -H "password: user123" -H "role: USER" -X POST http://localhost:8765/api/composite/ -H "Content-Type: application/json" -d "{"productId":102, "name":"User Product", "weight":50}"
```

###  Monitoring
```bash
# Vérification Eureka
curl http://localhost:8761

# Health Checks
curl http://localhost:8070/actuator/health
curl http://localhost:8081/actuator/health
curl http://localhost:8086/actuator/health
curl http://localhost:8083/actuator/health

# Métriques custom
curl -H "username: user" -H "password: user123" -H "role: USER" http://localhost:8070/actuator/metrics/http.requests
```

##  Guide de Démarrage

### Ordre de Démarrage
1. **Eureka Server** - mvn spring-boot:run (port 8761)  
2. **Config Server** - mvn spring-boot:run (port 8091)  
3. **Services Métier** - Product, Review, Recommendation  
4. **Authorization Service** - mvn spring-boot:run (port 8085)  
5. **Product-Composite** - mvn spring-boot:run (port 8070)  
6. **Gateway** - mvn spring-boot:run (port 8765)  

##  Stack Technique
- Spring Boot 3 + Spring Cloud 2022
- Spring Cloud Gateway - Routing & Sécurité
- Netflix Eureka - Service Discovery
- Resilience4j - Circuit Breaker Pattern
- Spring Cloud Config - Configuration Centralisée
- Spring Actuator - Monitoring & Métriques
- Feign Client - Communication REST
- Maven - Gestion des dépendances

##  Structure du Projet
```
tp-microservices-spring-cloud/
 eureka-server/          # Service Discovery (8761)
 config-server/          # Configuration Centralisée (8091)
 gateway-service/        # API Gateway & Sécurité (8765)
 authorization-service/  # Authentification (8085)
 product-service/        # Gestion Produits (8081)
 review-service/         # Gestion Avis (8086)
 recommendation-service/ # Gestion Recommandations (8083/8084)
 product-composite-service/ # Service Composite (8070)
 rest-client/            # Client de test (8095)
 README.md
```

##  Auteur
**Boujnan Yassine - Echbab Meryem**  
*Master AIDC - Université Sultan Moulay Slimane, FST Béni Mellal (Année 2025)*

 *Ce TP démontre une architecture microservices professionnelle avec les patterns et bonnes pratiques de Spring Cloud, incluant sécurité, résilience et scalabilité.*  
 *Prêt pour la production : déployable sur cloud avec Docker.*
