template <typename T1, typename T2, typename T3> class threee {
    T1 First;
    T2 Second;
    T3 Third;
public:
    T1& first() {
        return First;
    }
    T2& second() {
        return Second;
    }
    T3& third() {
        return Third;
    }

    void set(const T1& a, const T2& b, const T3& c) {
        First = a;
        Second = b;
        Third = c;
    }

    void swap(threee& a) {
        threee temp(*this);
        First = a.First;
        Second = a.Second;
        Third = a.Third;
        a.First = temp.First;
        a.Second = temp.Second;
        a.Third = temp.Third;
    }

    threee () {}
    threee (const threee& a): First(a.First), Second(a.Second), Third(a.Third) {}
    threee (const T1& a, const T2& b, const T3& c): First(a), Second(b), Third(c) {}
    ~threee() {}
    threee& operator=(threee a) {
        swap(a);
    }
};
