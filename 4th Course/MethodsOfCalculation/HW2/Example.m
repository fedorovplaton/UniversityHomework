clear; clc; close('all');

%% Inputs:
%% Matrix
A = [
        7.35272     0.88255     -2.170052;
        0.88255     5.58351     0.528167;
        -2.170052    0.528167    4.430329
     ];
 
 %% Right vector
 y = [
        1;
        0;
        0
     ];
%% Solve using LU defactorization
x = LUsystem(A, y);
disp('Answer:');
disp(x);

%% Solve using square root method
G = Gmatrix(A);
disp(G * G');
x = Gsystem(A, y);

disp('Answer:');
disp(x);