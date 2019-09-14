select UserName
	from User u
    where IsReviewer = '1';
    
select FirstName, LastName
	from User u
    where password = 'OneHeart27';
    
select Code, Name, PhoneNumber
	from Vendor
    where state = 'OH'
    order by Name;
    
