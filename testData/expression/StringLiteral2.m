t = 9
xlabel(['p(h | ' (t) ' )']); %% !!!!!!
if(isLik)
     xlabel(['p(' num2str(data(1:i)) ' | h)']);
 else
     xlabel(['p(h | ' num2str(data(1:i)) ' )']);
 end
