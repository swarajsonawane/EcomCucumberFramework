package Common_Resources;

public enum EC_Resources {
	
	LoginAPI("/api/ecom/auth/login"),
	CreateProductAPI("/api/ecom/product/add-product"),
	DeleteProductAPI("/api/ecom/product/delete-product/{productIdKey}"),
	placeOrderAPI("/api/ecom/order/create-order");
	
	private String Resourses;
	EC_Resources(String Resourses) {
		this.Resourses=Resourses;
	}
	
	public String getResourses() {
		
		return Resourses;
	}

}
