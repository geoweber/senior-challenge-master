package de.tarent.challenge.store.products;

class ProductValidator {

    private static ProductValidator instance = new ProductValidator();

    static ProductValidator getInstance() {
        return instance;
    }

    private ProductValidator() {
        super();
    }

    void validate(Product object) {
        if (object == null) {
            throw new ProductInvalidException("Product is null");
        }


        //- SKU: required, not empty, unique
        //- Name: required, not empty
        //- EANs: At least one, non-empty item
        //- Price: required, greater than 0


        if (object.getSku() == null|| object.getSku().trim().isEmpty()) {
            throw new ProductInvalidException("Product.sku is empty");
        }

        if (object.getName() == null|| object.getName().trim().isEmpty()) {
            throw new ProductInvalidException("Product.name is empty");
        }

        if (object.getPrice()<=0) {
            throw new ProductInvalidException("Product.price is illegal");
        }

        if (object.getEans() == null|| object.getEans().isEmpty()) {
            throw new ProductInvalidException("Product.EANs is empty");
        }


        boolean notFound=true;
        for (String ean:object.getEans()) {
            if (!ean.trim().isEmpty()){
                notFound=false;
            }
        }

        if(notFound){
            throw new ProductInvalidException("Product.EANs is empty");
        }

    }

}
