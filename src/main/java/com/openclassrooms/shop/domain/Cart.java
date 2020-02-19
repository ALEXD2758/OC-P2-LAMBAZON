   package com.openclassrooms.shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
	
	private List<CartLine> CartLineList = new ArrayList<CartLine>();

	/**
	 *
	 * @return the actual cartline list
	 */
	public List<CartLine> getCartLineList() {
		return CartLineList;
	}

    /**
     * Adds a getProductById in the cart or increment its quantity in the cart if already added
     * @param product getProductById to be added
     * @param quantity the quantity
     */
    public void addItem(Product product, int quantity) {
    	for (CartLine cl: getCartLineList())
    		if (cl.getProduct().equals(product)) {
    			cl.setQuantity(quantity + cl.getQuantity());
    		return;
    		}
    
    	CartLineList.add(new CartLine(product, quantity));
}

    /**
     * Removes a getProductById form the cart
     * @param product the getProductById to be removed
     */
    public void removeLine(Product product) {
        getCartLineList().removeIf(l -> l.getProduct().getId().equals(product.getId()));
    }


    /**
     * @return total value of a cart
     */
    public double getTotalValue()
	{
		double totalPrice = 0.0;
         
		for (CartLine cl: getCartLineList()) {
			totalPrice += (cl.getProduct().getPrice() * cl.getQuantity());
		}
		return totalPrice;
	}

    /**
     * @return Get average value of a cart
     */
    public double getAverageValue()
	{
        double sumprice = 0.0;
        double sumqty = 0.0;
        
        for(CartLine cl: getCartLineList()) {
        	sumprice += cl.getSubtotal();
        	sumqty += cl.getQuantity();
               
    }
    	return sumprice / sumqty;
	}
    

    /**
     * @param productId the getProductById id to search for
     * @return getProductById in the cart if it finds it
     */
	public Product findProductInCartLines(Long productId) {
    		for(CartLine cl: getCartLineList()) 
    			if(cl.getProduct().getId().equals(productId)) {
    			return cl.getProduct();
    		}
    		return null;
    }

    /**
     *
     * @param index of the cartLine
     * @return CartLine in that index
     */
    public CartLine getCartLineByIndex(int index)
    {
        return getCartLineList().get(index);
    }

    /**
     * Clears the cart of all added products
     */
    public void clear()
    {
        List<CartLine> cartLines = getCartLineList();
        cartLines.clear();
    }
}



