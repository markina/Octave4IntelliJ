include  = @(d)addpath(genpathPMTK(d));
fitFn   = @(X)hmmFit(X, nstates, 'gauss', fitArgs{:});
logprobFn = @hmmLogprob;
fitFn   = @(X)hmmFit(X, nstates, 'mixGaussTied', fitArgs{:});
include  = @(d)addpath(genpathPMTK(d));
funObj = @(W)SoftmaxLoss2(W,X,y,nClasses);
wSoftmax = minFunc(@penalizedL2,zeros((nVars+1)*(nClasses-1),1),options,funObj,lambda(:));
fn = @(x) x.^2;