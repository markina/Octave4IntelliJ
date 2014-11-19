Xtrain = [train4'; train5'];
model = struct('mu', muHat', 'Sigma', SigmaHat);
if(isLik)
           xlabel(['p(' num2str(data(1:i)) ' | h)']);
       else
           xlabel(['p(h | ' num2str(data(1:i)) ' )']);
       end
xlabel(['p(h | ' num2str(data) ' )']);
function hypothesis = explicitSet(set)
        hypothesis = [{set} {[' {' num2str(set) '}']} {size(set,2)}];
    end
hyps{i,1} = operator(cell2mat(hyp1(i,1))',cell2mat(hyp2(i,1))','rows')';
W = (X'*X + sqrt(lambda)*eye(size(X,2)))\(X'*y);
