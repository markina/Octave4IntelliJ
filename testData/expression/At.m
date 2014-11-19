include  = @(d)addpath(genpathPMTK(d));
fitFn   = @(X)hmmFit(X, nstates, 'gauss', fitArgs{:});
logprobFn = @hmmLogprob;
fitFn   = @(X)hmmFit(X, nstates, 'mixGaussTied', fitArgs{:});
include  = @(d)addpath(genpathPMTK(d));
funObj = @(W)SoftmaxLoss2(W,X,y,nClasses);
wSoftmax = minFunc(@penalizedL2,zeros((nVars+1)*(nClasses-1),1),options,funObj,lambda(:));
fn = @(x) x.^2;
hypFn        =  @mathHypothesesSmall;
priorFn      =  @mathPriorSmall;
likelihoodFn =  @likelihood;
posteriorFn  =  @posterior;
postPredFn   =  @postPredictive;
priorPredFn  =  @priorPredictive;

    hypSpace = [ evenOdd()
                 predicateBased( @(x)(x == round(sqrt(x)).^2),'squares')
                 multiples(3:10)
                 endingIn(1:9)
                 powers(2:10)
                 predicateBased(@(x)(x==x),'all')
                 compose(powers(2),explicitSet([37]),@union,' +')
                 compose(powers(2),explicitSet([32]),@setdiff,' -')
               ];

hypSpace = [    evenOdd()
                    predicateBased( @(x)(x == round(sqrt(x)).^2),'squares')
                    predicateBased( @(x)(x == round(x.^(1/3)).^3),'cubes')
                    predicateBased( @(x)(isprime(x)),'primes')
                    multiples(3:12)
                    powers(2:10)
                    endingIn(0:9)
                    allContiguousIntervals()
               ];
fplot(@(x)i,[first-1,last+1,1,length(names)+ 0.02],'-k');
f = @(x) sin(2*pi*x);
kernelFn = @kernelRbfSigma;


predictFn = @(Xtest) logregPredict(model, Xtest);
logregArgs.preproc.kernelFn = @(X1, X2)kernelRbfSigma(X1, X2, rbfScale);
logregArgs.preproc.kernelFn = @(X1, X2)kernelRbfSigma(X1, X2, rbfScale);
predictFn = @(Xtest) logregPredict(model, Xtest);
model = rvmFit(X, y, 'kernelFn', @(X1,X2) kernelFn(X1,X2,rbfScale));
predictFn = @(Xtest) rvmPredict(model, Xtest);
model = svmFit(X, y, 'C', C, 'kernel', 'rbf', ...
'kernelParam', gamma,'fitFn', @svmlightFit);
fname = 'SVM';
predictFn = @(Xtest) svmPredict(model, Xtest);


f = @(x)gaussProb(x, 0, 1) + gaussProb(x, 6, 1);