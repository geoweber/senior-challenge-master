package de.tarent.challenge.store.cart;




class CartValidator {

    //singleton construct

    private CartValidator(){
        super();
    }
    private static  CartValidator instance=new CartValidator();

    static CartValidator getInstance() {
        return instance;
    }



    /**
     * Requirements for a cart:
     * Contains a non-empty list of items
     * Each item can have a specific quantity
     *
     * @param object - cart to validate
     * @throws CartInvalidException - the cart is not correct
     */
    void validate(Cart object) throws CartInvalidException {

        if (object == null) {
            throw new CartInvalidException("Cart is null");
        }

        //Contains a non-empty list of items
        if (object.getProducts() == null || object.getProducts().isEmpty()) {
            throw new CartInvalidException("Cart contains an empty list of items");
        }

        //Each item can have a specific quantity
        for (CartItem item : object.getProducts()) {
            if (item.getQuantity() <= 0) {
                StringBuilder message = new StringBuilder();
                message.append("The quantity for product name/sku [");
                message.append(item.getProduct().getName());
                message.append("/");
                message.append(item.getProduct().getSku());
                message.append("] is illegal");
                throw new CartInvalidException(message.toString());
            }
        }

        if (object.isCheckedOut() && object.getCheckedDate() == null) {
            throw new CartInvalidException("Data inconsistency! The cart is checked bat CheckoutDate is null");
        }

    }

}
