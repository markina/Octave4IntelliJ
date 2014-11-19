%% Fit a discrete HMM via hmmFit and dgmTrain, comparing the results
%
%%

% This file is from pmtk3.googlecode.com

setSeed(0); 
obsModel = [1/6 , 1/6 , 1/6 , 1/6 , 1/6 , 1/6  ;   
           1/10, 1/10, 1/10, 1/10, 1/10, 5/10 ];   
transmat = [0.95 , 0.05;
           0.10  , 0.90];
pi = [0.5, 0.5];
T = 30; 
%% sample data
nsamples = 1;
