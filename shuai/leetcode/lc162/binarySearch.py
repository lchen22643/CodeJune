def binary_search(arr, x):
    lo = 0, hi = len(arr)
    while(lo < hi):
        mid = (lo + hi) / 2;
        if(arr[mid] < x):
            lo = mid + 1
        else:
            hi = mid
    return lo

a = [1, 2,3,4,7]
print (binary_search(a, 3))
print (binary_search(a, 5))
